package danazone.com.visu.ui.main.select;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import danazone.com.visu.BaseAdapter;
import danazone.com.visu.R;

public class SelectAdapter extends BaseAdapter {
    public interface OnSelectClickListener {
        void onClickItem(int position);
    }

    private OnSelectClickListener mListener;

    protected SelectAdapter(@NonNull Context context, OnSelectClickListener listener) {
        super(context);
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_select_item, parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    /**
     * ViewHolderItem
     */
    private class ViewHolderItem extends RecyclerView.ViewHolder {
        private TextView mTvStatus;


        public ViewHolderItem(View view) {
            super(view);
            mTvStatus = (TextView) view.findViewById(R.id.mTvStatus);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClickItem(getLayoutPosition());
                    }
                }
            });
        }
    }
}
