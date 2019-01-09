package job4j.ru.fragmentTask;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import job4j.ru.job4jexam.R;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 29/10/2018
 */
public class SecondFragment extends Fragment {
    private OnBackButtonClickListener callback;

    public interface OnBackButtonClickListener {
        void onBackButtonClicked(String message);
    }

    public void onClick(View view) {
        callback.onBackButtonClicked("Back button clicked");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        Button previousFragment = view.findViewById(R.id.back);
        previousFragment.setOnClickListener(v -> this.onClick(view));
        return view;
    }

    /**
     * Called when a fragment is associated with an operation (an Activity object is passed to it).
     *
     * @param context object Activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (SecondFragment.OnBackButtonClickListener) context; // Set activity by connecting fragment to activity
    }

    /**
     * Called when disconnect fragment with operation
     */
    @Override
    public void onDetach() {
        super.onDetach();
        callback = null; // Null link by disconnecting fragment from activity
    }
}
