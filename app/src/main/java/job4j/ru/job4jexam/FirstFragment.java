package job4j.ru.job4jexam;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 29/10/2018
 */
public class FirstFragment extends Fragment {
    private OnNextButtonClickListener callback;

    public interface OnNextButtonClickListener {
        void onNextButtonClicked(String message);
    }

    public void onClick(View view) {
        callback.onNextButtonClicked("Next button clicked");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String message = bundle.getString("message");
            TextView textView = view.findViewById(R.id.from_second_fragment);
            textView.setText(message);
        }
        Button nextFragment = view.findViewById(R.id.next_fragment);
        nextFragment.setOnClickListener(v -> {
            this.onClick(view);
        });
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
        callback = (OnNextButtonClickListener) context; // Set activity by connecting fragment to activity
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
