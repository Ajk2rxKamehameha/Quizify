package quizify.ajeet_meena.com.quizify.Utilities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import quizify.ajeet_meena.com.quizify.R;



public class Standard_Dialog extends DialogFragment implements View.OnClickListener
{

    private static final String KEY_TITLE = "title";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_NEGATIVEBUTTON = "negativeButton";
    private static final String KEY_POSITIVEBUTTON = "positiveButton";

    private TextView dialogTitle;
    private TextView dialogMessage;
    private Button dialogNegativeButton;
    private Button dialogPositiveButton;

    public static Standard_Dialog newInstance(String title, String message, String negativeButton, String positiveButton)
    {
        Standard_Dialog f = new Standard_Dialog();

        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putString(KEY_MESSAGE, message);
        args.putString(KEY_NEGATIVEBUTTON, negativeButton);
        args.putString(KEY_POSITIVEBUTTON, positiveButton);
        f.setArguments(args);

        return f;
    }

    public Standard_Dialog()
    {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_dialog_standard, null);

        dialogTitle = (TextView) dialogView.findViewById(R.id.dialogTitle);
        dialogMessage = (TextView) dialogView.findViewById(R.id.dialogMessage);
        dialogNegativeButton = (Button) dialogView.findViewById(R.id.dialogButtonNegative);
        dialogPositiveButton = (Button) dialogView.findViewById(R.id.dialogButtonPositive);

        dialogTitle.setText(getArguments().getString(KEY_TITLE));
        dialogMessage.setText(getArguments().getString(KEY_MESSAGE));
        dialogNegativeButton.setText(getArguments().getString(KEY_NEGATIVEBUTTON));
        dialogPositiveButton.setText(getArguments().getString(KEY_POSITIVEBUTTON));

        dialogNegativeButton.setOnClickListener(this);
        dialogPositiveButton.setOnClickListener(this);

        builder.setView(dialogView);
        Dialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.MyAnimation_Window;

        return dialog;
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.dialogButtonNegative)
        {

            if (getArguments().getString(KEY_NEGATIVEBUTTON).equals("BUG REPORT/SUGGEST"))
            {
                Suggest_Dialog suggest_dialog = Suggest_Dialog.newInstance("Bug Report/Suggest", "", "CANCEL", "SUBMIT");
                suggest_dialog.show(getFragmentManager(), "dialog");
            }
            dismiss();
        }
        if (v.getId() == R.id.dialogButtonPositive)
        {
            if(getArguments().getString(KEY_POSITIVEBUTTON).equals("OPEN SETTINGS"))
            {
                startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS), 0);
            }
            dismiss();
        }

    }
}