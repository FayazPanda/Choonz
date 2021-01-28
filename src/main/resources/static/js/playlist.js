let trackToAdd = 0;

var loggedIn = false;
var currentUsername = "";

if (loginCheck() == 0) {
    loggedIn = false;
} else {
    loggedIn = true;
    currentUsername = username();
}

var isMyPlaylist = false;

var playlistId = "";
var playlistName = "";
var playlistDesc = "";
var currentPlaylist = "";
var artworkURL = "";

var playlistTracks = "";

function getPlaylist(id) {
    fetch('http://localhost:8082/playlists/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function (playlistData) {
                    getTracks(id);
                    console.log(playlistData)
                    let title = document.getElementById("playlistTitle");
                    title.innerHTML = '';

                    let description = document.getElementById("description");
                    description.innerHTML = '';

                    let artwork = document.getElementById("playlistCover");
                    artwork.src = playlistData["artwork"]

                    let user = document.getElementById("userNameDiv");
                    user.innerHTML = '';

                    if (currentUsername == playlistData["user"]["username"]) {
                        isMyPlaylist = true;
                    }
                    //artist.innerHTML = "Created by: " + albumData["artist"]["name"];
                    user.insertAdjacentHTML("beforeend", '<p id="userName"> Created by: ' + playlistData["user"]["username"] + '</p>');
                    title.insertAdjacentHTML("beforeend", playlistData["name"]);
                    description.insertAdjacentHTML("beforeend", "Description: " + playlistData["description"]);

                    let trackList = playlistData["trackPlaylists"];
                    let table = document.getElementById("table")

                    let trackNumber = 1;
                    playlistId = playlistData["id"];
                    playlistName = playlistData["name"];
                    playlistDesc = playlistData["description"];
                    artworkURL = playlistData["artwork"];

                    if (isMyPlaylist == true || getPermission() == 1) {
                        let playlistDataHTML = document.getElementById("playlistData");
                        playlistDataHTML.insertAdjacentHTML("beforeend", '<button id="delete" style="visibility: visible" class="btn btn-danger" href="#" onclick="deletePlaylist();">Delete</button>')

                        playlistDataHTML.insertAdjacentHTML("beforeend", '<button class="btn btn-primary" data-toggle="modal" data-target="#editPlaylistModal" type="button">Edit Playlist</button>')
                    }

                    console.log(isMyPlaylist)
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function getTracks(id) {
    fetch('http://localhost:8082/trackPlaylist/findTracks/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function (trackData) {
                    console.log(trackData)
                    playlistTracks = trackData;
                    let trackNumber = 1;
                    if (isMyPlaylist == true || getPermission() == 1) {
                        for (let track of trackData) {
                            table.insertAdjacentHTML("beforeend", myTrackRow(trackNumber, track["id"], track["tracks"]["name"], duration(track["tracks"]["duration"])))
                            trackNumber++
                        }
                    } else {
                        for (let track of trackData) {
                            table.insertAdjacentHTML("beforeend", trackRow(trackNumber, track["tracks"]["id"], track["tracks"]["name"], duration(track["tracks"]["duration"])))
                            trackNumber++
                        }
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function putPlaylistData(listToEdit, data) {
    fetch('http://localhost:8082/playlists/update/' + listToEdit, {
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

function deleteTrackFromPlaylist(id) {
    fetch('http://localhost:8082/trackPlaylist/delete/'+id, {
        method: 'delete', //post, put,delete
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
            //fillPage();
        })
        .catch(function (error) {
            console.log('Request failed', error);
        })
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

function myTrackRow(trackNumber, id, name, duration) {
    return '<div class="tracks">\
                <button class="fas fa-trash trash" data-toggle="modal" data-target="#removeFromPlaylist" type="button" data-button="' + id + '"></button>\
                <a href="/tracks.html?id=' + id + '" class="track">\
                    <p>' + trackNumber + '</p>\
                    <p>' + name + '</p>\
                    <p>' + duration + '</p>\
                </a>\
            </div>'
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

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);
currentPlaylist = id;

getPlaylist(id);

$('#addToPlaylist').on('show.bs.modal', function (e) {
    var $trigger = $(e.relatedTarget);

    let id = $trigger.data('button');
    trackToAdd = id;
    myPlaylists();
})

$('#removeFromPlaylist').on('show.bs.modal', function (e) {
    var $trigger = $(e.relatedTarget);

    let id = $trigger.data('button');
    trackToAdd = id;
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

$(document).on("click", "#confirmDelete", function () {
    deleteTrackFromPlaylist(trackToAdd)
    location.reload();
});

$('#editPlaylistModal').on('show.bs.modal', function (e) {
    document.getElementById("playlist-name").value = playlistName;
    document.getElementById("description-text").value = playlistDesc;
    document.getElementById("artworkURL").value = artworkURL;
})

$(document).on("click", "#saveEditBtn", function () {
    //console.log("Add " + trackToAdd + " " + playlistId);
    data = {
        "name": document.getElementById("playlist-name").value,
        "description": document.getElementById("description-text").value,
        "artwork": document.getElementById("artworkURL").value
    }

    putPlaylistData(playlistId ,data);
    location.reload();
});

function deletePlaylist() {
    fetch("http://localhost:8082/playlists/delete/" + id, {
        method: 'delete',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
    window.location.replace("index.html");
}