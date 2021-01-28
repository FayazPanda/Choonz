package stepdef;


import com.selenium.pages.demosite.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class StepDefinitions {
    private static Index index;
    private static LogInPage logInPage;
    private static Album album;
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        index = new Index(driver);
        logInPage = new LogInPage(driver);
        album = new Album(driver);
        //index = PageFactory.initElements(driver, Index.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    //--------------------------------------
    // NOT LOGGED IN
    //--------------------------------------
    @Given("^the correct web address$")
    public void the_correct_web_address() throws Throwable {
        driver.get(Index.URL);

    }

    @When("^I am on the homepage$")
    public void i_am_on_the_homepage() throws Throwable {
        // Index index = new Index(driver);
    }

    @Then("^I can see top albums$")
    public void i_can_see_top_albums() throws Throwable {
        //Index index = new Index(driver);
        //
        assertTrue(index.returnTopAlbum().contains("21"));
    }

    @When("^I click on Genre$")
    public void i_click_on_Genre() throws Throwable {

        driver.get(Index.GenreURL);

    }

    @Then("^I can see all genres$")
    public void i_can_see_all_genres() throws Throwable {
        //Index index = new Index(driver);
        //
        assertEquals("Pop", index.getTopGenre().getText());
    }

    @When("^I click on Albums$")
    public void i_click_on_Albums() throws Throwable {

        driver.get(Index.AlbumURL);
    }


    @Then("^I can see all albums$")
    public void i_can_see_all_albums() throws Throwable {
        // Index index = new Index(driver);
        //
        assertEquals("21", index.getBrowserAlbum().getText());
    }

    @When("^I click on Artists$")
    public void i_click_on_Artists() throws Throwable {

        driver.get(Index.ArtistURL);
    }


    @Then("^I can see all artists$")
    public void i_can_see_all_artists() throws Throwable {
        //Index index = new Index(driver);
        //
        assertEquals("Adele", index.getBrowserArtist().getText());
    }

    @When("^I click on All Playlists$")
    public void i_click_on_All_Playlists() throws Throwable {
        // Index index = new Index(driver);
        driver.get(Index.AllPlaylistURL);
    }

    @Then("^I can see all playlists$")
    public void i_can_see_all_playlists() throws Throwable {
        //Index index = new Index(driver);
        //
        assertTrue(index.getBrowserPlaylist().getText().contains("Another Day, Another Generic Playlist"));
    }

    @When("^I type a term into the search bar$")
    public void i_type_a_term_into_the_search_bar() throws Throwable {
        //Index index = new Index(driver);
        //
        index.search();

    }

    @Then("^I can see the correct result$")
    public void i_can_see_the_correct_result() throws Throwable {
        //
        //Index index = new Index(driver);
        System.out.println(index.getSearchResult().getText());
        assertTrue(index.getSearchResult().getText().contains("Because the Internet"));
    }

    @When("^I register$")
    public void i_register() throws Throwable {
        driver.get(Index.URL);
        index.navLogIn();
        Thread.sleep(1500);
        logInPage.register();
        Thread.sleep(1500);
    }

    @Then("^I can see my new username$")
    public void i_can_see_my_new_username() throws Throwable {
        Index index2 = new Index(driver);
        assertEquals("Hi, testy", index2.returnMessage());

    }

    //--------------------------------------
// LOGGED IN
//--------------------------------------
    @Given("^the correct web address and logged in$")
    public void the_correct_web_address_and_logged_in() throws Throwable {
        driver.get(Index.URL);
        index.navLogIn();
        Thread.sleep(1500);
        logInPage.logIn();
        Thread.sleep(1500);

    }

    @When("^I go to homepage$")
    public void i_go_to_homepage() throws Throwable {
        driver.get(Index.URL);


    }

    @Then("^I can see my username$")
    public void i_can_see_my_username() throws Throwable {

        Index index2 = new Index(driver);
        assertEquals("Hi, test", index2.returnMessage());


    }

    @When("^I click on My Playlists$")
    public void i_click_on_My_Playlists() throws Throwable {

        driver.get(Index.MyPlaylistsURL);
    }

    @Then("^I can see all my playlists$")
    public void i_can_see_all_my_playlists() throws Throwable {
        //
        // driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        MyPlaylists my = new MyPlaylists(driver);
        //
        my.clickPlaylist();
        Thread.sleep(500);
        assertTrue(driver.getPageSource().contains("Another Day, Another Generic Playlist"));

    }

    @When("^I click on create playlist and fill in the form$")
    public void i_click_on_create_playlist_and_fill_in_the_form() throws Throwable {

        driver.get(Index.CreatePlaylistURL);
        //
        CreatePlaylist create = new CreatePlaylist(driver);
        //
        create.create();
        //
    }

    @Then("^I can see my created playlist$")
    public void i_can_see_my_created_playlist() throws Throwable {

        driver.get(Index.URL + "/playlist.html?id=6");
        //
        Thread.sleep(1500);
        MyPlaylists my = new MyPlaylists(driver);
        //

        assertEquals("Test Playlist", my.getPlaylistName().getText());
    }

    @When("^I add a song to a playlist$")
    public void i_add_a_song_to_a_playlist() throws Throwable {
        driver.get(Index.URL + "/album.html?id=3");
        Album album = new Album(driver);
        album.addToPlaylist();
    }

    @Then("^I can see my correctly updated playlist$")
    public void i_can_see_my_correctly_updated_playlist() throws Throwable {
        driver.get(Index.URL + "/playlist.html?id=1");
        Playlist playlist = new Playlist(driver);
        assertTrue(playlist.getTable().getText().contains("Aerodynamic"));
    }

    @When("^I remove a song from a playlist$")
    public void i_remove_a_song_from_a_playlist() throws Throwable {
        Thread.sleep(1500);
        driver.get(Index.URL + "/playlist.html?id=1");
        Thread.sleep(1500);
        Playlist playlist = new Playlist(driver);
        playlist.deleteFrom();
    }

    @Then("^I can see the correctly updated playlist$")
    public void i_can_see_the_correctly_updated_playlist() throws Throwable {
        driver.get(Index.URL + "/playlist.html?id=1");
        Playlist playlist = new Playlist(driver);
        assertFalse(playlist.getTable().getText().contains("Rolling in the Deep"));
    }

    //-------------------------
// READ
//----------------------
    @When("^I click an album$")
    public void i_click_an_album() throws Throwable {
        // Index index = new Index(driver);
        //
        index.clickTopAlbum();
    }

    @Then("^I can see album page$")
    public void i_can_see_album_page() throws Throwable {

        assertEquals("21", album.getAlbumTitle().getText());
    }

    @When("^I click an artist$")
    public void i_click_an_artist() throws Throwable {
        // Index index = new Index(driver);
        //
        index.clickTopAlbum();
        // Album album = new Album(driver);
        album.clickArtist();
    }

    @Then("^I can see the artist page$")
    public void i_can_see_the_artist_page() throws Throwable {
        Artist artist = new Artist(driver);
        assertEquals("Artist: Adele", artist.getArtistName().getText());
    }

    @When("^I click on genre$")
    public void i_click_on_genre() throws Throwable {
        //Index index = new Index(driver);
        driver.get(Index.URL + "/browse.html?page=genres");
        BrowseGenre browseGenre = new BrowseGenre(driver);
        browseGenre.clickPop();


    }

    @Then("^I can see the genre page$")
    public void i_can_see_the_genre_page() throws Throwable {
        Genre genre = new Genre(driver);
        assertEquals("Genre: Pop", genre.getGenreTitle().getText());

    }

    @When("^I click on a track$")
    public void i_click_on_a_track() throws Throwable {
        // Index index = new Index(driver);
        //
        index.clickTopAlbum();
        //
        // Album album = new Album(driver);
        album.clickTrack();
    }

    @Then("^I can see the track page$")
    public void i_can_see_the_track_page() throws Throwable {
        Track track = new Track(driver);
        assertEquals("Rolling in the Deep", track.getTrackName().getText());
    }

    //--------------------------------------
// UPDATE
//--------------------------------------
    @Given("^I have logged in as admin$")
    public void i_have_logged_in_as_admin() throws Throwable {
        driver.get(Index.URL);
        // Index index = new Index(driver);
        //
        index.navLogIn();
        Thread.sleep(1500);
        LogInPage logInPage = new LogInPage(driver);

        logInPage.logInAdmin();
        Thread.sleep(1500);
        //
    }

    @When("^I update an album$")
    public void i_update_an_album() throws Throwable {
        //
        driver.get(Index.URL + "/album.html?id=1");
        //
        Album album = new Album(driver);
        album.clickAlbumEdit();
        //

    }

    @Then("^I can see updated album page$")
    public void i_can_see_updated_album_page() throws Throwable {
        driver.get(Index.URL + "/album.html?id=1");
        //
        Album albumEdit = new Album(driver);
        assertEquals("21Test", albumEdit.getAlbumTitle().getText());
    }

    @When("^I update an artist$")
    public void i_update_an_artist() throws Throwable {
        //
        driver.get(Index.URL + "/artist.html?id=1");
        //
        Artist artist = new Artist(driver);
        artist.editArtist();


    }

    @Then("^I can see the updated artist page$")
    public void i_can_see_the_updated_artist_page() throws Throwable {

        driver.get(Index.URL + "/artist.html?id=1");

        Artist artist = new Artist(driver);
        assertEquals("Artist: AdeleTest", artist.getArtistName().getText());
    }

    @When("^I update on genre$")
    public void i_update_on_genre() throws Throwable {

        driver.get(Index.URL + "/genre.html?id=1");

        Genre genre = new Genre(driver);
        genre.editGenre();

    }

    @Then("^I can see the updated genre page$")
    public void i_can_see_the_updated_genre_page() throws Throwable {
        driver.get(Index.URL + "/genre.html?id=1");
        Genre genre = new Genre(driver);
        assertEquals("Genre: PopTest", genre.getGenreTitle().getText());
    }

    @When("^I update on a track$")
    public void i_update_on_a_track() throws Throwable {

        driver.get(Index.URL + "/tracks.html?id=1");

        Track track = new Track(driver);
        track.editTrack();

    }

    @Then("^I can see the updated track page$")
    public void i_can_see_the_updated_track_page() throws Throwable {
        Track track = new Track(driver);
        assertEquals("Rolling in the DeepTest", track.getTrackName().getText());
    }

//--------------------------------------
// DELETE
//--------------------------------------

    @When("^I delete a playlist$")
    public void i_delete_a_playlist() throws Throwable {

        driver.get(Index.URL + "/playlist.html?id=1");

        Playlist playlist = new Playlist(driver);
        playlist.delete();

    }

    @Then("^I can see the playlist is not there$")
    public void i_can_see_the_playlist_is_not_there() throws Throwable {
        driver.get(Index.URL + "/browse.html?page=playlists");


        assertFalse(driver.getPageSource().contains("Another Day, Another Generic Playlist"));
    }


    @When("^I delete an album$")
    public void i_delete_an_album() throws Throwable {

        driver.get(Index.URL + "/album.html?id=1");

        Album album = new Album(driver);
        album.deleteAlbum();

    }

    @Then("^I can see the album is not there$")
    public void i_can_see_the_album_is_not_there() throws Throwable {
        driver.get(Index.URL + "/browse.html?page=albums");


        assertFalse(driver.getPageSource().contains("21"));
    }

    @When("^I delete an artist$")
    public void i_delete_an_artist() throws Throwable {

        driver.get(Index.URL + "/artist.html?id=1");

        Artist artist = new Artist(driver);
        artist.delete();

    }

    @Then("^I can see the artist is not there$")
    public void i_can_see_the_artist_is_not_there() throws Throwable {
        driver.get(Index.URL + "/browse.html?page=artists");


        assertFalse(driver.getPageSource().contains("Adele"));
    }

    @When("^I delete on genre$")
    public void i_delete_on_genre() throws Throwable {

    }

    @Then("^I can see the genre is not there$")
    public void i_can_see_the_genre_is_not_there() throws Throwable {

    }

    @When("^I delete on a track$")
    public void i_delete_on_a_track() throws Throwable {

        driver.get(Index.URL + "/tracks.html?id=11");

        Track track = new Track(driver);
        track.delete();

    }

    @Then("^I can see the track is not there$")
    public void i_can_see_the_track_is_not_there() throws Throwable {
        driver.get(Index.URL + "/album.html?id=2");

        Album album = new Album(driver);
        assertFalse(album.getWrapper().getText().contains("The Library"));
    }

//---------------------------------------------
// CREATE
//---------------------------

    @When("^I create an album$")
    public void i_create_an_album() throws Throwable {
        driver.get(Index.URL + "/addAlbum.html");
        CreateAlbum createAlbum = new CreateAlbum(driver);
        createAlbum.create();

    }

    @Then("^I can see created album$")
    public void i_can_see_created_album() throws Throwable {
        //Thread.sleep(1500);
        driver.get(Index.URL + "/browse.html?page=albums");
        Thread.sleep(1500);
        WebElement all = driver.findElement(By.id("all"));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(all));

        assertTrue(all.getText().contains("Test Album"));
    }

    @When("^I create an artist$")
    public void i_create_an_artist() throws Throwable {
        driver.get(Index.URL + "/addArtist.html");
        CreateArtist createArtist = new CreateArtist(driver);
        createArtist.create();
    }

    @Then("^I can see the created artist$")
    public void i_can_see_the_created_artist() throws Throwable {
        driver.get(Index.URL + "/browse.html?page=artists");
        WebElement all = driver.findElement(By.id("all"));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(all));
        assertTrue(all.getText().contains("Test Artist"));
    }

    @When("^I create a genre$")
    public void i_create_a_genre() throws Throwable {
        driver.get(Index.URL + "/addGenre.html");
        CreateGenre createGenre = new CreateGenre(driver);
        createGenre.create();
    }

    @Then("^I can see the created genre$")
    public void i_can_see_the_created_genre() throws Throwable {
        driver.get(Index.URL + "/browse.html?page=genres");
        WebElement all = driver.findElement(By.id("all"));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(all));
        assertTrue(all.getText().contains("Test Genre"));
    }

    @When("^I create a track$")
    public void i_create_a_track() throws Throwable {
        driver.get(Index.URL + "/album.html?id=1");
        Thread.sleep(1500);
        Album album = new Album(driver);
        album.addTrack();
        CreateTrack createTrack = new CreateTrack(driver);
        createTrack.create();
    }

    @Then("^I can see the created track$")
    public void i_can_see_the_created_track() throws Throwable {
        driver.get(Index.URL + "/album.html?id=1");

        Album album = new Album(driver);
        assertTrue(driver.getPageSource().contains("Test Track"));
    }


//	   @Before
//	    public void setup() {
//	        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
//	        driver = new ChromeDriver();
//	        driver.manage().window().setSize(new Dimension(1366, 768));
//
//	    }
//	    @After
//	    public void tearDown() {
//	        driver.close();
//	    }


}
