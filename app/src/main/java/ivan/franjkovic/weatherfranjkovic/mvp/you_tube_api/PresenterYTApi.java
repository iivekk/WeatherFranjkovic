package ivan.franjkovic.weatherfranjkovic.mvp.you_tube_api;


import ivan.franjkovic.weatherfranjkovic.youtubeapi_models.YouTubeResult;

public class PresenterYTApi implements ContractYTApi.PresenterYTA, ContractYTApi.ModelYTA.OnFinishedListener {

    private ContractYTApi.ViewYTA v;
    private ContractYTApi.ModelYTA model;

    public PresenterYTApi(ContractYTApi.ViewYTA v) {
        this.v = v;
        this.model = new ModelYTApi();
    }

    @Override
    public void onFinished(YouTubeResult result) {
        v.setVideoCode(result.getItems().get(0).getId().getVideoId());
    }

    @Override
    public void onFailure(Throwable t) {
        v.onResponseFailureYTA(t.getMessage());
    }

    @Override
    public void onDestroyYTA() {
        this.v = null;
    }

    @Override
    public void requestDataFromServerYTA(String keyWord) {
        model.getResult(this, keyWord);
    }
}
