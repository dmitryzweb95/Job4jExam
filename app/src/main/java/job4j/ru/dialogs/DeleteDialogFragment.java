package job4j.ru.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 16/11/2018
 */
public class DeleteDialogFragment extends DialogFragment {
    private DeleteDialogFragmentListener callback;

    public interface DeleteDialogFragmentListener {
        void onPositiveDialogClick(DialogFragment dialog);

        void onNegativeDialogClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage("Delete all exams?")
                .setPositiveButton(android.R.string.ok, (dialogInterface, which) -> {
                    callback.onPositiveDialogClick(this);
                }) // Button OK
                .setNegativeButton(android.R.string.cancel, (dialog1, which) -> {
                    callback.onNegativeDialogClick(this);
                }) // Button cancel
                .create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (DeleteDialogFragment.DeleteDialogFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement DeleteDialogFragmentListener",
                    context.toString()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
