package resembrink.dev.devresembrink;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyValueSelector extends RelativeLayout {

    private int minValue= Integer.MIN_VALUE;
    private int maxValue=Integer.MAX_VALUE;

    TextView valueTextView, labelTextView;
    ImageView plusButton, minusButton;

    View rootview;

    public MyValueSelector(Context context) {
        super(context);
    }

    public MyValueSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyValueSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attr)
    {
        rootview = inflate(context,R.layout.value_selector,this );
        valueTextView= rootview.findViewById(R.id.valueTextView);
        labelTextView= rootview.findViewById(R.id.labelTextView);
        plusButton= rootview.findViewById(R.id.plusButton);
        minusButton= rootview.findViewById(R.id.minusButton);

        TypedArray ta= context.getTheme().obtainStyledAttributes(attr, R.styleable.MyValueSelector,0,0);

        float labelTextSize= ta.getDimension(R.styleable.MyValueSelector_labelTextSize,0);

        if(labelTextSize > 0)
        {
            labelTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,labelTextSize);

        }

        float numTextSize= ta.getDimension(R.styleable.MyValueSelector_valueTextSize,0);

        if(numTextSize > 0)
        {
            valueTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,numTextSize);

        }

        String label=ta.getString(R.styleable.MyValueSelector_labelText);

        if(label != null && !label.isEmpty())
        {
            labelTextView.setText(label);

        }

        plusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementValue();
            }
        });
        
        minusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementValue();
                
            }
        });

    }

    private void decrementValue() {

        int currentValue =Integer.parseInt(valueTextView.getText().toString());
        if(currentValue > minValue)
        {
            valueTextView.setText(String.valueOf(currentValue-1));
        }
    }

    private void incrementValue() {

        int currentValue =Integer.parseInt(valueTextView.getText().toString());
        if(currentValue < maxValue)
        {
            valueTextView.setText(String.valueOf(currentValue+1));
        }
    }

}
