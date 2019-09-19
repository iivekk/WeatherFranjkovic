package ivan.franjkovic.weatherfranjkovic.geonames_restapi;

import ivan.franjkovic.weatherfranjkovic.geonames_api_models.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.PATH_TO_URL_CONTENT;

public interface ApiService {

    // http://api.geonames.org/searchJSON?name_startsWith=oroslavje&maxRows=20&lang=hr&username=iivekk;
    @GET(PATH_TO_URL_CONTENT)
    Call<SearchResult> SEARCH_RESULT_CALL(
            @Query("name_startsWith") String query,
            @Query("maxRows") int maxRows,
            @Query("lang") String language,
            @Query("username") String username);
}
