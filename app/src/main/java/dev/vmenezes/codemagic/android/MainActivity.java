package dev.vmenezes.codemagic.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public final class MainActivity extends Activity {
    private static final String STATE_COUNT = "count";
    private Counter counter;
    private TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Android 15 pode desenhar o app atrás das barras do sistema.
        findViewById(R.id.content).setOnApplyWindowInsetsListener((view, insets) -> {
            view.setPadding(
                    view.getPaddingLeft(), insets.getSystemWindowInsetTop(),
                    view.getPaddingRight(), insets.getSystemWindowInsetBottom());
            return insets;
        });
        findViewById(R.id.content).requestApplyInsets();

        counter = new Counter(savedInstanceState == null
                ? 0 : savedInstanceState.getInt(STATE_COUNT));
        countView = findViewById(R.id.count);
        findViewById(R.id.increment).setOnClickListener(view -> {
            counter.increment();
            renderCount();
        });
        findViewById(R.id.reset).setOnClickListener(view -> {
            counter.reset();
            renderCount();
        });
        renderCount();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_COUNT, counter.getValue());
        super.onSaveInstanceState(outState);
    }

    private void renderCount() {
        countView.setText(getString(R.string.count_value, counter.getValue()));
    }
}
