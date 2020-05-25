package digiplus.ma.mycitytour_rabat;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Lifestyle_Fragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    public Lifestyle_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.data_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        //final ArrayList<DATA> LifeStyle_Name = getResources().getStringArray(R.array.Monuments);//new ArrayList<DATA>();
        final ArrayList<DATA> LifeStyle_Name = new ArrayList<DATA>();

        LifeStyle_Name.add(new DATA(R.string.so, R.string.address,
                R.drawable.so, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.Villam, R.string.address,
                R.drawable.villam, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.latable, R.string.address,
                R.drawable.latable, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.palmiers, R.string.address,
                R.drawable.lestrois, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.chill, R.string.address,
                R.drawable.chill, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.dhow, R.string.address,
                R.drawable.dhow, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.golden, R.string.address,
                R.drawable.golden, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.alwarda, R.string.address,
                R.drawable.alwarda, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.marsa, R.string.address,
                R.drawable.mersa, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.amber, R.string.address,
                R.drawable.amber, R.raw.welcome));
        LifeStyle_Name.add(new DATA(R.string.gotham, R.string.address,
                R.drawable.gotham, R.raw.welcome));

        DATA_Adapter adapter = new DATA_Adapter(getActivity(), LifeStyle_Name, R.color.colorAccent);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // Set a click listener
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                DATA data_ = LifeStyle_Name.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), data_.getMusicResource());
                    mMediaPlayer.start(); // Start the audio file

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Releasing MP.
     */

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
