package com.changhong.sdk.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.changhong.sdk.R;

public class PullRefreshListView extends ListView implements OnScrollListener
{
    
    //	private static final String TAG = "listview";
    
    private final static int RELEASE_To_REFRESH = 0;
    
    private final static int PULL_To_REFRESH = 1;
    
    private final static int REFRESHING = 2;
    
    private final static int DONE = 3;
    
    private final static int LOADING = 4;
    
    // 实际的padding的距离与界面上偏移距离的比例
    private final static int RATIO = 1;
    
    private LayoutInflater inflater;
    
    private LinearLayout headView;
    
    private TextView tipsTextview;
    
    private TextView lastUpdatedTextView;
    
    private ImageView arrowImageView;
    
    private ProgressBar progressBar;
    
    private RotateAnimation animation;
    
    private RotateAnimation reverseAnimation;
    
    // 用于保证startY的值在一个完整的touch事件中只被记录一次
    private boolean isRecored;
    
    private int headContentWidth;
    
    private int headContentHeight;
    
    private int startY;
    
    private int firstItemIndex;
    
    private int state;
    
    private boolean isBack;
    
    private OnRefreshListener refreshListener;
    
    private NewScrollerListener newScrollerListener;
    
    private boolean isRefreshable;
    
    /**listview下面的加载更多*/
    private LinearLayout llFootViewLoadmore;
    
    /**
     * 是否正在获取更多
     */
    private boolean isLoadingMore = false;
    
    public PullRefreshListView(Context context)
    {
        super(context);
        init(context);
    }
    
    public PullRefreshListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }
    
    private void init(Context context)
    {
        // setCacheColorHint(context.getResources().getColor(R.color.transparent));
        inflater = LayoutInflater.from(context);
        
        headView = (LinearLayout) inflater.inflate(R.layout.head, null);
        
        llFootViewLoadmore = (LinearLayout) inflater.inflate(R.layout.view_sms_footer,
                null);
        
        //设置不可点击
        llFootViewLoadmore.setEnabled(false);
        TextView tv = (TextView) llFootViewLoadmore.findViewById(R.id.tv_footer_view);
        tv.setText(R.string.common_pagetip_loading);
        
        arrowImageView = (ImageView) headView.findViewById(R.id.head_arrowImageView);
        arrowImageView.setMinimumWidth(70);
        arrowImageView.setMinimumHeight(50);
        progressBar = (ProgressBar) headView.findViewById(R.id.head_progressBar);
        tipsTextview = (TextView) headView.findViewById(R.id.head_tipsTextView);
        lastUpdatedTextView = (TextView) headView.findViewById(R.id.head_lastUpdatedTextView);
        
        measureView(headView);
        headContentHeight = headView.getMeasuredHeight();
        headContentWidth = headView.getMeasuredWidth();
        
        headView.setPadding(0, -1 * headContentHeight, 0, 0);
        headView.invalidate();
        
        Log.d("size", "width:" + headContentWidth + " height:"
                + headContentHeight);
        
        addHeaderView(headView, null, false);
        
        animation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);
        
        reverseAnimation = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(200);
        reverseAnimation.setFillAfter(true);
        
        state = DONE;
        isRefreshable = false;
    }
    
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount)
    {
        firstItemIndex = firstVisibleItem;
        if (newScrollerListener != null)
        {
            newScrollerListener.newScroll(view,
                    firstVisibleItem,
                    visibleItemCount,
                    totalItemCount);
        }
    }
    
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        if (newScrollerListener != null)
        {
            newScrollerListener.newScrollChanged(view, scrollState);
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        
        if (isRefreshable)
        {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    if (firstItemIndex == 0 && !isRecored)
                    {
                        isRecored = true;
                        startY = (int) event.getY();
                    }
                    break;
                
                case MotionEvent.ACTION_UP:

                    if (state != REFRESHING && state != LOADING)
                    {
                        //					if (state == DONE) {
                        //						// 什么都不做
                        //					}
                        if (state == PULL_To_REFRESH)
                        {
                            state = DONE;
                            changeHeaderViewByState();
                            
                        }
                        if (state == RELEASE_To_REFRESH)
                        {
                            state = REFRESHING;
                            changeHeaderViewByState();
                            onRefresh();
                        }
                    }
                    
                    isRecored = false;
                    isBack = false;
                    
                    break;
                
                case MotionEvent.ACTION_MOVE:

                    //将纵坐标除以2，防止下拉的时候，下拉出来的面积增长过快，即手指所点的地方下移时没有跟手指移动一致，速度比手指快 modify by kuangbiao
                    int tempY = (int) event.getY() / 2;
                    
                    if (!isRecored && firstItemIndex == 0)
                    {
                        isRecored = true;
                        startY = tempY;
                    }
                    
                    if (state != REFRESHING && isRecored && state != LOADING)
                    {
                        
                        // 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动
                        
                        // 可以松手去刷新了
                        if (state == RELEASE_To_REFRESH)
                        {
                            
                            setSelection(0);
                            
                            // 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
                            if (((tempY - startY) / RATIO < headContentHeight)
                                    && (tempY - startY) > 0)
                            {
                                state = PULL_To_REFRESH;
                                changeHeaderViewByState();
                            }
                            // 一下子推到顶了
                            else if (tempY - startY <= 0)
                            {
                                state = DONE;
                                changeHeaderViewByState();
                            }
                            // 往下拉了，或者还没有上推到屏幕顶部掩盖head的地步
                            //						else {
                            //							// 不用进行特别的操作，只用更新paddingTop的值就行了
                            //						}
                        }
                        // 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
                        if (state == PULL_To_REFRESH)
                        {
                            
                            setSelection(0);
                            
                            // 下拉到可以进入RELEASE_TO_REFRESH的状态
                            if ((tempY - startY) / RATIO >= headContentHeight)
                            {
                                state = RELEASE_To_REFRESH;
                                isBack = true;
                                changeHeaderViewByState();
                            }
                            // 上推到顶了
                            else if (tempY - startY <= 0)
                            {
                                state = DONE;
                                changeHeaderViewByState();
                            }
                        }
                        
                        // done状态下
                        if (state == DONE)
                        {
                            if (tempY - startY > 0)
                            {
                                state = PULL_To_REFRESH;
                                changeHeaderViewByState();
                            }
                        }
                        
                        // 更新headView的size
                        if (state == PULL_To_REFRESH)
                        {
                            headView.setPadding(0, -1 * headContentHeight
                                    + (tempY - startY) / RATIO, 0, 0);
                            
                        }
                        
                        // 更新headView的paddingTop
                        if (state == RELEASE_To_REFRESH)
                        {
                            headView.setPadding(0, (tempY - startY) / RATIO
                                    - headContentHeight, 0, 0);
                        }
                        
                    }
                    
                    break;
            }
        }
        
        return super.onTouchEvent(event);
    }
    
    // 当状态改变时候，调用该方法，以更新界面
    private void changeHeaderViewByState()
    {
        switch (state)
        {
            case RELEASE_To_REFRESH:
                arrowImageView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tipsTextview.setVisibility(View.VISIBLE);
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                
                arrowImageView.clearAnimation();
                arrowImageView.startAnimation(animation);
                
                tipsTextview.setText(R.string.filemgt_upload_pagetip_releasetorefresh);
                
                break;
            case PULL_To_REFRESH:
                progressBar.setVisibility(View.GONE);
                tipsTextview.setVisibility(View.VISIBLE);
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();
                arrowImageView.setVisibility(View.VISIBLE);
                // 是由RELEASE_To_REFRESH状态转变来的
                if (isBack)
                {
                    isBack = false;
                    arrowImageView.clearAnimation();
                    arrowImageView.startAnimation(reverseAnimation);
                    
                    tipsTextview.setText(R.string.filemgt_upload_pagetip_pulldownrefresh);
                }
                else
                {
                    tipsTextview.setText(R.string.filemgt_upload_pagetip_pulldownrefresh);
                }
                break;
            
            case REFRESHING:

                headView.setPadding(0, 0, 0, 0);
                
                progressBar.setVisibility(View.VISIBLE);
                arrowImageView.clearAnimation();
                arrowImageView.setVisibility(View.GONE);
                tipsTextview.setText(getResources().getString(R.string.filemgt_upload_pagetip_refreshing));
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                
                break;
            case DONE:
                headView.setPadding(0, -1 * headContentHeight, 0, 0);
                
                progressBar.setVisibility(View.GONE);
                arrowImageView.clearAnimation();
                arrowImageView.setImageResource(R.drawable.ic_pulltorefresh_arrow);
                tipsTextview.setText(R.string.filemgt_upload_pagetip_pulldownrefresh);
                lastUpdatedTextView.setVisibility(View.VISIBLE);
                
                break;
        }
    }
    
    public void setOnRefreshListener(OnRefreshListener onRefreshListener)
    {
        this.refreshListener = onRefreshListener;
        isRefreshable = true;
    }
    
    public interface OnRefreshListener
    {
        public void onRefresh();
    }
    
    public void onRefreshComplete()
    {
        state = DONE;
        hideFootView();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd  HH:mm");
        String date = format.format(new Date());
        lastUpdatedTextView.setText(getResources().getString(R.string.filemgt_upload_pagetip_recentupdate)
                + date);
        changeHeaderViewByState();
    }
    
    public void onRefreshComplete(String updateTime)
    {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd  HH:mm");
        Date date = new Date(Long.parseLong(updateTime));
        String time = "";
        time = format.format(date);
        lastUpdatedTextView.setText(getResources().getString(R.string.filemgt_upload_pagetip_recentupdate)
                + time);
    }
    
    private void onRefresh()
    {
        if (refreshListener != null)
        {
            refreshListener.onRefresh();
        }
    }
    
    // 此方法直接照搬自网络上的一个下拉刷新的demo，此处是“估计”headView的width以及height
    private void measureView(View child)
    {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null)
        {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0)
        {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        }
        else
        {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
    
    public void setAdapter(BaseAdapter adapter)
    {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd  HH:mm");
        String date = format.format(new Date());
        lastUpdatedTextView.setText(getResources().getString(R.string.filemgt_upload_pagetip_recentupdate)
                + date);
        super.setAdapter(adapter);
    }
    
    public interface NewScrollerListener
    {
        
        public void newScroll(AbsListView view, int firstVisibleItem,
                int visibleItemCount, int totalItemCount);
        
        public void newScrollChanged(AbsListView view, int scrollState);
        
    }
    
    public void setNewScrollerListener(NewScrollerListener newScrollerListener)
    {
        this.newScrollerListener = newScrollerListener;
        setOnScrollListener(this);
        
    }
    
    /** <全部加载完成>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void showLoadFinish()
    {
        hideFootView();
        // showFootView();
        // ((TextView) llFootViewLoadmore.findViewById(R.id.tv_footer_view)).setText(R.string.common_load_finish);
        //  llFootViewLoadmore.findViewById(R.id.pb_footer_view).setVisibility(View.GONE);
    }
    
    /** <加载更多失败>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void showLoadFail()
    {
        showFootView();
        // ((TextView) llFootViewLoadmore.findViewById(R.id.tv_footer_view)).setText(R.string.common_loadmore_fail);
        llFootViewLoadmore.findViewById(R.id.pb_footer_view)
                .setVisibility(View.GONE);
    }
    
    /** <显示加载更多中>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void showLoading()
    {
        isLoadingMore = true;
        //hideFootView();
        showFootView();
        // llFootViewLoadmore.setVisibility(View.GONE);
        // ((TextView) llFootViewLoadmore.findViewById(R.id.tv_footer_view)).setText(R.string.common_loadmore_ing);
        // llFootViewLoadmore.findViewById(R.id.pb_footer_view).setVisibility(View.GONE);
    }
    
    /**
     *是否正在获取更多
     * [功能详细描述]
     * @return
     */
    public boolean isLoadingMore()
    {
        return isLoadingMore;
    }
    
    /** <隐藏footview>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void hideFootView()
    {
        llFootViewLoadmore.setVisibility(View.GONE);
        removeFooterView(llFootViewLoadmore);
        isLoadingMore = false;
    }
    
    public void showFootView()
    {
        llFootViewLoadmore.setVisibility(View.VISIBLE);
        //显示前先remove原来的
        if (getAdapter() != null)
        {
            removeFooterView(llFootViewLoadmore);
        }
        addFooterView(llFootViewLoadmore, null, false);
    }
    
    public boolean isRefreshable()
    {
        return isRefreshable;
    }
    
    public void setRefreshable(boolean isRefreshable)
    {
        this.isRefreshable = isRefreshable;
    }
    
}