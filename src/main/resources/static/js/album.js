let trackToAdd = 0;

var loggedIn = false;
if (loginCheck() == 0) {
    loggedIn = false;
} else {
    loggedIn = true;
}

var albumTitle = "";
var albumGenre = "";
var albumCoverURL = "";

let artistID = "";

function getAlbum(id) {
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
                    console.log(albumData)
                    let title = document.getElementById("albumTitle");
                    title.innerHTML = '';

                    let artist = document.getElementById("artistNameDiv");
                    artist.innerHTML = '';

                    let genre = document.getElementById("albumGenreDiv");
                    genre.innerHTML = '';

                    let albumCover = document.getElementById("albumCover");
                    albumCover.src = albumData["cover"];

                    albumTitle = albumData["name"];
                    albumGenre = albumData["genre"]["id"];
                    albumCoverURL = albumData["cover"]

                    artistID = albumData["artist"]["id"];
                    artist.insertAdjacentHTML("beforeend", '<a href="/artist.html?id=' + albumData["artist"]["id"] + '"><p id="artistName"> Artist: ' + albumData["artist"]["name"] + '</p></a>');
                    title.insertAdjacentHTML("beforeend", albumData["name"]);
                    genre.insertAdjacentHTML("beforeend", '<a href="/genre.html?id=' + albumData["genre"]["id"] + '"><p id="albumGenre"> Genre: ' + albumData["genre"]["name"] + '</p></a>');

                    let trackList = albumData["tracks"];
                    let table = document.getElementById("table")
                    let trackNumber = 1;
                    if (getPermission() == 1) {
                        let tracks = document.getElementById("tracks");
                        tracks.insertAdjacentHTML("afterend", '<button class="btn btn-success" data-toggle="modal" data-target="#editTrackModal" type="button">Add Track</button>');

                    }
                    for (let track of trackList) {
                        table.insertAdjacentHTML("beforeend", trackRow(trackNumber, track["id"], track["name"], duration(track["duration"])))

                        trackNumber++;
                    }


                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function addTrackToPlaylist(data) {
    fetch('http://localhost:8082/trackPlaylist/create', {
        method: 'post',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(data)
    })
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
        })
        .catch(function (error) {
            console.log('Request failed', error);
        })
}

function myPlaylists() {
    fetch('http://localhost:8082/playlists/search/?search=user.id:' + userId())
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

                    for (let playlist of data) {
                        list.insertAdjacentHTML("beforeend", '<option value="' + playlist.id + '">' + playlist.name + '</option>')
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function trackRow(trackNumber, id, name, duration) {
    if (loggedIn) {
        return '<div class="tracks">\
                <button class="fas fa-plus plus" data-toggle="modal" data-target="#addToPlaylist" type="button" data-button="' + id + '"></button>\
                <a href="/tracks.html?id=' + id + '" class="track">\
                    <p>' + trackNumber + '</p>\
                    <p>' + name + '</p>\
                    <p>' + duration + '</p>\
                </a>\
            </div>'
    }

    return '<div class="tracks">\
                <button class="fas fa-plus plus" data-toggle="modal" data-target="#notLoggedIn" type="button" data-button="' + id + '"></button>\
                <a href="/tracks.html?id=' + id + '" class="track">\
                    <p>' + trackNumber + '</p>\
                    <p>' + name + '</p>\
                    <p>' + duration + '</p>\
                </a>\
            </div>'

}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');

getAlbum(id);

$('#addToPlaylist').on('show.bs.modal', function (e) {
    var $trigger = $(e.relatedTarget);

    let id = $trigger.data('button');
    trackToAdd = id;
    myPlaylists();
})

$(document).on("click", "#addTrack", function () {
    let playlistId = document.getElementById("listSelect").value;

    let data = {
        "tracks": {
            "id": trackToAdd
        },
        "playlists": {
            "id": playlistId
        }
    }
    console.log("Add " + trackToAdd + " " + playlistId);
    addTrackToPlaylist(data);
});

$(document).on("click", "#saveTrackBtn", function () {
    let name = document.getElementById("track-name").value;
    let duration = document.getElementById("duration").value;
    let lyrics = document.getElementById("lyrics").value;
    //console.log("Add " + trackToAdd + " " + playlistId);
    let data = {
        "name": name,
        "duration": duration,
        "lyrics": lyrics,
        "artist": {
            "id": artistID
        },
        "genre": {
            "id": albumGenre
        },
        "album": {
            "id": id
        }
    }
    postTrackData(data);

});

function postTrackData(data) {
    fetch('http://localhost:8082/tracks/create/', {
        method: 'post',
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
                    let list = document.getElementById("genreListSelect");
                    list.innerHTML = "";

                    for (let genre of data) {
                        list.insertAdjacentHTML("beforeend", '<option value="' + genre["id"] + '">' + genre["name"] + '</option>')
                    }

                    let selectGenre = albumGenre;
                    document.getElementById("genreListSelect").value = selectGenre;

                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function putAlbumData(albumToEdit, data) {
    fetch('http://localhost:8082/albums/update/' + albumToEdit, {
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

// Delete function
function deleteAlbum() {
    fetch("http://localhost:8082/albums/delete/" + id, {
        method: 'delete',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
    window.location.replace("index.html");
}

$('#editAlbumModal').on('show.bs.modal', function (e) {
    getGenres();
    document.getElementById("album-title").value = albumTitle;
    document.getElementById("album-artwork").value = albumCoverURL;
    // document.getElementById("genreListSelect").value = selectGenre;
    // console.log(selectGenre);
})

$(document).on("click", "#saveEditBtn", function () {

    data = {
        "id": id,
        "name": document.getElementById("album-title").value,
        "cover": document.getElementById("album-artwork").value,
        "genre": {
            "id": document.getElementById("genreListSelect").value
        },
        "artist": {
            "id": artistID
        }
    }
    console.log(data)
    putAlbumData(id, data);

    console.log("SAVED")
    location.reload();
});