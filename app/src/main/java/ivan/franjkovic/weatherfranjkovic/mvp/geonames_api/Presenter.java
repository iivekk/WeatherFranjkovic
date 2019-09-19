package ivan.franjkovic.weatherfranjkovic.mvp.geonames_api;

import java.util.ArrayList;
import java.util.List;

import ivan.franjkovic.weatherfranjkovic.geonames_api_models.Geoname;
import ivan.franjkovic.weatherfranjkovic.geonames_api_models.SearchResult;

public class Presenter implements Contract.Presenter, Contract.Model.OnFinishedListener {

    private Contract.View mView;
    private Contract.Model mModel;

    public Presenter(Contract.View mView) {
        this.mView = mView;
        this.mModel = new Model();
    }

    @Override
    public void onFinished(SearchResult results) {
        mView.setDataToRecyclerView(cityList(results.getGeonames()));
    }

    @Override
    public void onFailure(Throwable t) {
        mView.onResponseFailure(t.toString());
    }

    @Override
    public void message(String message) {
        mView.setMessage(message);
    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }

    @Override
    public void requestDataFromServer(String query) {
        mModel.getResult(this, query);
    }

    private List<Geoname> cityList(List<Geoname> list) {
        List<Geoname> newList = new ArrayList<>();
        for (Geoname g : list) {
            if (g.getFcl().equals("P")) {
                newList.add(g);
            }
        }
        return newList;
    }
}
