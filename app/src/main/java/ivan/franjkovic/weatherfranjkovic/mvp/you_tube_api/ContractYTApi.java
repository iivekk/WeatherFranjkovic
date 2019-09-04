package ivan.franjkovic.weatherfranjkovic.mvp.you_tube_api;

import ivan.franjkovic.weatherfranjkovic.youtubeapi_models.YouTubeResult;

public interface ContractYTApi {

    interface ModelYTA {

        interface OnFinishedListener {

            void onFinished(YouTubeResult result);

            void onFailure(Throwable t);


        }

        void getResult(OnFinishedListener listener, String keyWord);

    }

    interface ViewYTA {

        void setVideoCode(String code);

        void onResponseFailureYTA(String message);

    }

    interface PresenterYTA {

        void onDestroyYTA();

        void requestDataFromServerYTA(String keyWord);

    }

}
