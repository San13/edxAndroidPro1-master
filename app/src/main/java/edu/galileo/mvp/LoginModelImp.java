package edu.galileo.mvp;

import android.os.AsyncTask;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import edu.galileo.mvp.event.CanceledEvent;
import edu.galileo.mvp.event.PasswordErrorEvent;
import edu.galileo.mvp.event.SuccessEvent;

/**
 * Created by san on 10/18/17.
 */

public class LoginModelImp implements LoginModel {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[] {
            "test@galileo.edu:testtest", "test2@galileo.edu:testtest"
    };

    @Override
    public void login(String username, String password) {
        // Login task
    new UserLoginTask(username,password).execute((Void) null);

    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {


            if (success) {
                EventBus.getDefault().post(new SuccessEvent());
            } else {
                EventBus.getDefault().post(new PasswordErrorEvent());
            }
        }

        @Override
        protected void onCancelled() {
          EventBus.getDefault().post(new CanceledEvent());
        }
    }
}
