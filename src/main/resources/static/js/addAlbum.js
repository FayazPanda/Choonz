const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');

function getArtists() {
    fetch('http://localhost:8082/artists/read')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (data) {
                    console.log(data)
                    let list = document.getElementById("listSelect");
                    list.innerHTML = "";

                    for (let artist of data) {
                        list.insertAdjacentHTML("beforeend", '<option value="' + artist.id + '">' + artist.name + '</option>')
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function getGenres() {
    fetch('http://localhost:8082/genres/read')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (data) {
                    console.log(data)
                    let list = document.getElementById("listSelectGenre");
                    list.innerHTML = "";

                    for (let genre of data) {
                        list.insertAdjacentHTML("beforeend", '<option value="' + genre.id + '">' + genre.name + '</option>')
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

getArtists();
getGenres();
let formElements = document.querySelector("form.artistForm").elements;


document
    .querySelector("form.artistForm")
    .addEventListener("submit", function (stop) {
        stop.preventDefault();


        let formElements = document.querySelector("form.artistForm").elements;

        let name = formElements["name"].value;

        let art = formElements["art"].value;
        let artistID = formElements["listSelect"].value;
        let genre = formElements["listSelectGenre"].value;
        //let artist = formElements["artist"].value;


        // JSON object for data
        let data = {

            "name": name,
            "cover": art,
            "genre": {
                "id": genre
            },
            "artist": {
                "id": artistID
            }

        }

        addAlbum(data);
        window.location.replace("browse.html?page=albums")
        //console.log(data);


    });

function addAlbum(data) {
    fetch('http://localhost:8082/albums/create', {
        method: 'post',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(data)
    })
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
            fillPage();
        })
        .catch(function (error) {
            console.log('Request failed', error);
        })
}