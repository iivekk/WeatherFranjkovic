package ivan.franjkovic.weatherfranjkovic.mvp.geonames_api;

import java.util.List;

import ivan.franjkovic.weatherfranjkovic.geonames_api_models.Geoname;
import ivan.franjkovic.weatherfranjkovic.geonames_api_models.SearchResult;

public interface Contract {

    interface Model {

        interface OnFinishedListener {

            void onFinished(SearchResult results);

            void onFailure(Throwable t);

            void message(String message);

        }

        void getResult(OnFinishedListener onFinishedListener, String query);

    }

    interface View {

        void setMessage(String message);

        void setDataToRecyclerView(List<Geoname> resultList);

        void onResponseFailure(String errorMsg);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer(String query);

    }

}
