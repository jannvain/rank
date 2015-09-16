package meal.rank.app;

import meal.rank.app.config.root.RootContextConfig;
import meal.rank.app.config.root.TestConfiguration;
import meal.rank.app.dto.MealDTO;
import meal.rank.app.model.Meal;
import meal.rank.app.model.SearchResult;
import meal.rank.app.services.MealService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Arrays;
import java.util.List;

import static meal.rank.app.TestUtils.date;
import static meal.rank.app.TestUtils.time;
import static meal.rank.app.dto.MealDTO.mapFromMealEntity;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, RootContextConfig.class})
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @PersistenceContext
    private EntityManager em;
/*
    @Test
    public void testFindMealsByDate() {
        SearchResult<Meal> result = mealService.findMeals(UserServiceTest.USERNAME, date(2015,1,1), date(2015,1,2), null ,null, 1);
        assertTrue("results not expected, total " + result.getResultsCount(), result.getResultsCount() == 3);
    }

    @Test
    public void testFindMealsByDateTime() {
        SearchResult<Meal> result = mealService.findMeals(UserServiceTest.USERNAME, date(2015,1,4), date(2015,1,4),
                time("12:00") ,time("16:00"), 1);
        assertTrue("results not expected, total " + result.getResultsCount(), result.getResultsCount() == 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromDateAfterToDate() {
        mealService.findMeals(UserServiceTest.USERNAME, date(2015,1,2), date(2015,1,1), null ,null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromTimeAfterToTime() {
        mealService.findMeals(UserServiceTest.USERNAME, date(2015,1,2), date(2015,1,1), time("12:00") ,time("11:00"), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromDateNull() {
        mealService.findMeals(UserServiceTest.USERNAME, null, date(2015,1,1), time("12:00") ,time("11:00"), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void toDateNull() {
        mealService.findMeals(UserServiceTest.USERNAME, date(2015,1,1), null, time("12:00") ,time("11:00"), 1);
    }

    @Test
    public void deleteMeals() {
        mealService.deleteMeals(Arrays.asList(4L));
        Meal meal = em.find(Meal.class, 4L);
        assertNull("meal was not deleted" , meal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteMealsNull() {
        mealService.deleteMeals(null);
    }
*/
    @Test
    public void saveMeals() {
        MealDTO meal1 = mapFromMealEntity(em.find(Meal.class, 1L), 0L);
        MealDTO meal2 = mapFromMealEntity(em.find(Meal.class, 2L), 0L);

        meal1.setDescription("test1");

        List<MealDTO> meals = Arrays.asList(meal1, meal2);

        mealService.saveMeals(UserServiceTest.USERNAME, meals);


        Meal m1 = em.find(Meal.class, 1L);
        assertTrue("description not as expected: " + m1.getDescription(), "test1".equals(m1.getDescription()));


    }


}
