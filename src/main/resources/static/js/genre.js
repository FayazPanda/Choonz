var genreTitle = "";
var genreDesc = "";

function getGenre(id) {
    fetch('http://localhost:8082/genres/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (genreData) {
                    console.log(genreData)
                    let genreName = document.getElementById("genreName");
                    genreName.innerHTML = "Genre: " + genreData["name"];

                    genreTitle = genreData["name"];
                    genreDesc = genreData["description"];

                    let gallery = document.getElementById("allAlbums");
                    gallery.innerHTML = '';

                    let description = document.getElementById("genreDescription");
                    description.innerHTML = '';
                    description.innerHTML = "Description: " + genreData["description"]

                    for (let album of genreData["albums"]) {
                        gallery.insertAdjacentHTML("beforeend", albumCard(album["id"], album["name"], album["cover"]));
                        getAlbumArtist(album.id)
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function getAlbumArtist(id) {
    fetch('http://localhost:8082/albums/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (albumData) {
                    let tile = document.getElementById(id);
                    tile.innerHTML = "By: " + albumData.artist.name;
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function putGenre(id, data) {
    fetch('http://localhost:8082/genres/update/' + id, {
        method: 'put', //post, put,delete
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(data)
    })
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
            //fillPage();
        })
        .catch(function (error) {
            console.log('Request failed', error);
        })
}

function albumCard(id, title, cover) {
    return '<div class="tile">\
    <a href="/album.html?id=' + id + '">\
    <img id="albumCover" src="' + cover + '">\
    <h3>' + title + '</h3>\
    <p id=' + id + '>By:</p>\
    </a>\
</div>'
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getGenre(id);

$('#editGenreModal').on('show.bs.modal', function (e) {
    document.getElementById("genre-title").value = genreTitle;
    document.getElementById("desc").value = genreDesc;
    // document.getElementById("genreListSelect").value = selectGenre;
    // console.log(selectGenre);
})

$(document).on("click", "#saveEditBtn", function () {
    //let playlistId = document.getElementById("listSelect").value;
    //console.log("Add " + trackToAdd + " " + playlistId);
    data = {
        "name": document.getElementById("genre-title").value,
        "description": document.getElementById("desc").value
    }
    putGenre(id, data);

    location.reload();
});