package ivan.franjkovic.weatherfranjkovic.activities;


import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ivan.franjkovic.weatherfranjkovic.R;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.VIDEO_CODE;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.YT_API_KEY;

public class VideoActivity extends YouTubeBaseActivity {

    @BindView(R.id.ytView)
    YouTubePlayerView ytView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        String code = getIntent().getStringExtra(VIDEO_CODE);

        if (code != null) {
            playVideo(code);
        }

    }

    private void playVideo(final String videoCode) {
        ytView.initialize(YT_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    youTubePlayer.loadVideo(videoCode);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(VideoActivity.this, youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
