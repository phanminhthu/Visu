package danazone.com.visu.ui.main.info.choose;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import danazone.com.visu.BaseAdapter;
import danazone.com.visu.R;
import danazone.com.visu.bean.Users;

public class ChooseTuTorAdapter extends BaseAdapter {
    public interface OnSelectClickListener {
        void onClickItem(Users position);
    }

    private OnSelectClickListener mListener;
    private List<Users> mList;

    protected ChooseTuTorAdapter(@NonNull Context context, List<Users> list, OnSelectClickListener listener) {
        super(context);
        this.mListener = listener;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item_tutor, parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderItem((ViewHolderItem) holder, position);

    }

    /**
     * onBindHolder Item
     *
     * @param holder
     * @param position
     */
    private void onBindViewHolderItem(ViewHolderItem holder, int position) {
        final Users chooseTuTo = mList.get(position);
        holder.mTvName.setText(chooseTuTo.getName());
        holder.mTvExperience.setText(String.valueOf(chooseTuTo.getExperience()));
        holder.mTvSchool.setText(chooseTuTo.getSchool());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickItem(chooseTuTo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * ViewHolderItem
     */
    private class ViewHolderItem extends RecyclerView.ViewHolder {
        private TextView mTvName;
        private TextView mTvExperience;
        private TextView mTvSchool;
        private ImageView mImgAvatar;
        private LinearLayout mView;

        public ViewHolderItem(View view) {
            super(view);
            mTvName = (TextView) view.findViewById(R.id.mTvName);
            mTvExperience = (TextView) view.findViewById(R.id.mTvExperience);
            mTvSchool = (TextView) view.findViewById(R.id.mTvSchool);
            mImgAvatar = (ImageView) view.findViewById(R.id.mImgAvatar);
            mView = (LinearLayout) view.findViewById(R.id.mView);
        }
    }
}

