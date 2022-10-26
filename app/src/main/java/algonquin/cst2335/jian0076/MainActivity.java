package algonquin.cst2335.jian0076;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is a program to validate the password
 * @author wenjiejiang
 * @version1.0
 */
public class MainActivity extends AppCompatActivity {
    /**This  holds the text at the center of the screen*/
    private TextView tv = null;
    /**This  holds the text of the password*/
    private EditText et = null;
    /**This  holds the text of login*/
   private Button bt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editText);
        Button bt = findViewById(R.id.button);

        bt.setOnClickListener(clk -> {
            String password = et.getText().toString();
            if (checkPasswordComplexity(password)){
                tv.setText("Your password meets the requirements");
            }
            else {
                tv.setText("You shall not pass!");
            }
        });
    }
        /**This function is used to validate the format of password
         * @param pw The String object that we are checking
         * @return return true if the password is complex enough, and return false if its not complex enough.
         */
        boolean checkPasswordComplexity (String pw) {
            boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
            foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

            for (int i = 0; i < pw.length(); i++) {

                if (Character.isUpperCase(pw.charAt(i))) {
                    foundUpperCase = true;
                }
                if (Character.isLowerCase(pw.charAt(i))) {
                    foundLowerCase = true;
                }
                if (Character.isDigit(pw.charAt(i))) {
                    foundNumber = true;
                }
                if (isSpecialCharacter(pw.charAt(i))) {
                    foundSpecial = true;
                }

            }
            if (!foundUpperCase) {

                Toast.makeText(getApplicationContext(), "The password must contain UpperCase letters;", Toast.LENGTH_SHORT).show();// Say that they are missing an upper case letter;

                return false;

            } else if (!foundLowerCase) {
                Toast.makeText(getApplicationContext(), "The password must contain lowerCase letters;", Toast.LENGTH_SHORT).show(); // Say that they are missing a lower case letter;

                return false;

            } else if (!foundNumber) {
                Toast.makeText(getApplicationContext(), "The password must contain numbers;", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!foundSpecial) {
                Toast.makeText(getApplicationContext(), "The password must contain special character;", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        }


    /**
     * This function is used to check if there is special character in the password.
     * @param c The character is being checked.
     * @return Return true if c is special character
     */
            private boolean isSpecialCharacter(char c)
            {
                switch(c)
                {
                    case '?':
                    case '!':
                    case '@':
                    case '$':
                    case '%':
                    case '&':
                    case '*':
                    case '#':
                    case '^':
                        return true;
                    default:
                        return false;

        }
    }
}