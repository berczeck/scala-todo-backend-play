package filters

import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

/**
  * Created by HP on 15/01/2017.
  */
class CustomCorsFilter @Inject()(corsFilter: CORSFilter) extends DefaultHttpFilters(corsFilter) {

}
