
package ivan.franjkovic.weatherfranjkovic.geonames_api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    @SerializedName("totalResultsCount")
    @Expose
    private long totalResultsCount;
    @SerializedName("geonames")
    @Expose
    private List<Geoname> geonames = null;

    public long getTotalResultsCount() {
        return totalResultsCount;
    }

    public void setTotalResultsCount(long totalResultsCount) {
        this.totalResultsCount = totalResultsCount;
    }

    public List<Geoname> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<Geoname> geonames) {
        this.geonames = geonames;
    }

}
