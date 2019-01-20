package com.example.demo4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo4.R;
import com.example.demo4.bean.ShowShopBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.ButterKnife;

public class ShowShopAdapter extends XRecyclerView.Adapter<ShowShopAdapter.ViewHolder> {

    private Context context;
    private List<ShowShopBean.DataBean> showlist;

    public ShowShopAdapter(Context context, List<ShowShopBean.DataBean> showlist) {
        this.context = context;
        this.showlist = showlist;
    }
    public void setList(List<ShowShopBean.DataBean> beans, int page) {
        if (page == 1) {
            this.showlist.clear();
        }

        if(beans != null){
            this.showlist.addAll(beans);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.show_item, null);
        ButterKnife.bind(this,itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ShowShopBean.DataBean dataBean = showlist.get(i);
        String images = dataBean.getImages();
        String[] split = images.split("!");
       viewHolder. svdShow.setImageURI(split[0]);
       viewHolder. tvShowname.setText(dataBean.getTitle());
       viewHolder. tvShowprice.setText(dataBean.getPrice()+"");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnitemClickLis.itemclick(dataBean.getPid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return showlist.size();
    }


    public class ViewHolder extends XRecyclerView.ViewHolder {
        SimpleDraweeView svdShow;
        TextView tvShowname;
        TextView tvShowprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           svdShow= itemView.findViewById(R.id.svd_show);
           tvShowname= itemView.findViewById(R.id.tv_showname);
           tvShowprice= itemView.findViewById(R.id.tv_showprice);

        }
    }
        public void setOnitemClickLis(OnitemClickLis onitemClickLis) {
            mOnitemClickLis = onitemClickLis;
        }

        OnitemClickLis mOnitemClickLis;

        public interface OnitemClickLis{
            void itemclick(int pid);
        }
}
