/**
 * Data Mining Project - Infrastructure in York Region
 *
 * Description:
 * This is a Java application used to process the below datasets from Open Markham Database.
 * There are 5 tools the user can choose from to display the data in a useful and interactive
 * manner.
 *
 * Datasets from Open Markham Database:
 * - Profile of Housing by Dissemination Area 2016 Census
 *   https://data-markham.opendata.arcgis.com/datasets/york::profile-of-housing-by-dissemination-area-2016-census/about
 *
 * - Profile of Journey to Work by Dissemination Area 2016 Census
 *   https://data-markham.opendata.arcgis.com/datasets/york::profile-of-journey-to-work-by-dissemination-area-2016-census/about
 *
 * @date-started: May 5th, 2021
 * @date-submitted: May 26th, 2021
 * @class: ICS-4U1 Period 4
 * @author Felix, Brandon, Daniel, Steven, Sean
 * @teacher Mr. Fernandes
 */

package application;

import controller.ApplicationController;

public class Main {
    
    public static void main (String[] args) {
    
        new ApplicationController();
    
    }
    
}
