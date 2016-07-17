package training.demogradlegitlab;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView spanningTV;

    /**
     * Produce a formatted SpannableString object from a given String
     * input, with all lowercase characters converted to smallcap
     * characters. Uses only standard A-Z characters, so works with
     * any font.
     *
     * @param input The input string, e.g. "Small Caps"
     * @return A formatted SpannableString, e.g. "Sᴍᴀʟʟ Cᴀᴘs"
     */
    public static SpannableString getSmallCapsString(String input) {
        // values needed to record start/end points of blocks of lowercase letters
        char[] chars = input.toCharArray();
        int currentBlock = 0;
        int[] blockStarts = new int[chars.length];
        int[] blockEnds = new int[chars.length];
        boolean blockOpen = false;

        // record where blocks of lowercase letters start/end
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                if (!blockOpen) {
                    blockOpen = true;
                    blockStarts[currentBlock] = i;
                }
                // replace with uppercase letters
                chars[i] = (char) (c - 'a' + '\u0041');
            } else {
                if (blockOpen) {
                    blockOpen = false;
                    blockEnds[currentBlock] = i;
                    ++currentBlock;
                }
            }
        }

        // add the string end, in case the last character is a lowercase letter
        blockEnds[currentBlock] = chars.length;

        // shrink the blocks found above
        SpannableString output = new SpannableString(String.valueOf(chars));
        for (int i = 0; i < Math.min(blockStarts.length, blockEnds.length); ++i) {
            output.setSpan(new RelativeSizeSpan(0.8f), blockStarts[i], blockEnds[i], Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }

        return output;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spanningTV = (TextView) findViewById(R.id.spanningTV);


        SpannableString sp = new SpannableString(getSmallCapsString("lat N  00°00.00000'\n" +
                "lon W 000°00.00000'\n" +
                "alt         00000ft"));

        // sp.setSpan(new RelativeSizeSpan(2f), 0, 5, 0);
        //sp.setSpan(new ForegroundColorSpan(Color.BLUE), 5, sp.length(), 0);
        sp.setSpan(new RelativeSizeSpan(.72f), 0, 3, 0);
        sp.setSpan(new CustomTypefaceSpan("", Typeface.createFromAsset(getAssets(), "RobotoMono-Regular.ttf")), 0, 3, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.RED), 4, 19, 0);


        sp.setSpan(new RelativeSizeSpan(.72f), 20, 23, 0);
        sp.setSpan(new CustomTypefaceSpan("", Typeface.createFromAsset(getAssets(), "RobotoMono-Regular.ttf")), 20, 23, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.RED), 24, 39, 0);


        sp.setSpan(new RelativeSizeSpan(.72f), 40, 43, 0);
        sp.setSpan(new CustomTypefaceSpan("", Typeface.createFromAsset(getAssets(), "RobotoMono-Regular.ttf")), 40, 43, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        sp.setSpan(new ForegroundColorSpan(Color.RED), 43, sp.length(), 0);

        spanningTV.setText(sp);
        spanningTV.setTypeface(Typeface.createFromAsset(getAssets(), "RobotoMono-Medium.ttf"));

    }






}
