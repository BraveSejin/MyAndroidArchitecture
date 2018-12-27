package com.tistory.black_jin0427.myandroidarchitecture.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tistory.black_jin0427.myandroidarchitecture.R;
import com.tistory.black_jin0427.myandroidarchitecture.api.model.User;
import com.tistory.black_jin0427.myandroidarchitecture.databinding.ItemPersonBinding;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.PersonHolder> {

    private List<User> items;

    private OnItemClickListener listener;

    @NonNull
    @Override
    public MainAdapter.PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);

        return new PersonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.PersonHolder personHolder, int position) {

        User user = items.get(personHolder.getAdapterPosition());

        personHolder.binding.setUser(user);
        personHolder.binding.setListener(listener);

        /*Glide.with(personHolder.itemView.getContext())
                .load(user.getPicture().getLarge())
                .into(personHolder.ivItemProfile);

        personHolder.tvItemName.setText(user.getFullName());
        personHolder.tvItemPhone.setText(user.getPhone());
        personHolder.tvItemMail.setText(user.getEmail());
        personHolder.tvItemLikeCnt.setText(String.valueOf(user.getLikeCnt()));

        personHolder.itemView.setOnClickListener(view
                -> listener.onClick(user));*/

    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void updateView(User user) {

        int pos = getPosition(user);

        if(pos == RecyclerView.NO_POSITION) return;

        items.set(pos, user);
        notifyItemChanged(pos);
    }

    private int getPosition(User user) {
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getFullName().equals(user.getFullName())) {
                return i;
            }
        }
        return RecyclerView.NO_POSITION;
    }

    public void setItems(List<User> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onClick(User user);
    }

    class PersonHolder extends RecyclerView.ViewHolder {

        ItemPersonBinding binding;

        /*@BindView(R.id.iv_item_profile)
        CircleImageView ivItemProfile;

        @BindView(R.id.tv_item_name)
        TextView tvItemName;

        @BindView(R.id.tv_item_phone)
        TextView tvItemPhone;

        @BindView(R.id.tv_item_mail)
        TextView tvItemMail;

        @BindView(R.id.tv_item_like_cnt)
        TextView tvItemLikeCnt;*/

        PersonHolder(View itemView) {
            super(itemView);
            //ButterKnife.bind(this, itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
