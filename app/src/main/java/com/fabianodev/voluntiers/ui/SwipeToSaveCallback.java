package com.fabianodev.voluntiers.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.fabianodev.voluntiers.R;
import com.google.android.material.snackbar.Snackbar;

abstract public class SwipeToSaveCallback extends ItemTouchHelper.Callback {

    private final ColorDrawable mBackground;
    private final Drawable saveDrawable;
    private final int intrinsicWidth;
    private final int intrinsicHeight;
    Context mContext;

    protected SwipeToSaveCallback(Context context) {
        mContext = context;
        mBackground = new ColorDrawable();
        mBackground.setColor(Color.parseColor("#008000")); // Cor de fundo para salvar (verde, por exemplo)
        saveDrawable = ContextCompat.getDrawable(mContext, R.drawable.baseline_check_circle_outline_24);
        intrinsicWidth = saveDrawable != null ? saveDrawable.getIntrinsicWidth() : 0;
        intrinsicHeight = saveDrawable != null ? saveDrawable.getIntrinsicHeight() : 0;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();
        mBackground.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + (int) dX, itemView.getBottom());
        mBackground.draw(c);

        int saveIconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
        int saveIconMargin = (itemHeight - intrinsicHeight) / 2;
        int saveIconLeft = itemView.getLeft() + saveIconMargin;
        int saveIconRight = itemView.getLeft() + saveIconMargin + intrinsicWidth;
        int saveIconBottom = saveIconTop + intrinsicHeight;

        saveDrawable.setBounds(saveIconLeft, saveIconTop, saveIconRight, saveIconBottom);
        saveDrawable.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        Snackbar snackbar = Snackbar.make(linearLayout, "O item foi salvo.", Snackbar.LENGTH_LONG);
        snackbar.setAction("Cancelar", view -> snackbar.dismiss());
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }
}
