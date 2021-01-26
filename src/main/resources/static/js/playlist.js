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

var playlistName = "";
var playlistDesc = "";

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
                    console.log(playlistData)
                    let title = document.getElementById("playlistTitle");
                    title.innerHTML = '';

                    let description = document.getElementById("description");
                    description.innerHTML = '';

                    let artwork = document.getElementById("playlistCover");
                    artwork.src = playlistData["artwork"]

                    let user = document.getElementById("userNameDiv");
                    user.innerHTML = '';

                    if(currentUsername == playlistData["user"]["username"]){
                        isMyPlaylist = true;
                    }
                    //artist.innerHTML = "Created by: " + albumData["artist"]["name"];
                    user.insertAdjacentHTML("beforeend", '<p id="userName"> Created by: ' + playlistData["user"]["username"] + '</p>');
                    title.insertAdjacentHTML("beforeend", playlistData["name"]);
                    description.insertAdjacentHTML("beforeend", "Description: " + playlistData["description"]);

                    let trackList = playlistData["trackPlaylists"];
                    let table = document.getElementById("table")

                    let trackNumber = 1;
                    if(isMyPlaylist==true || getPermission()==1){
                        playlistName = playlistData["name"];
                        playlistDesc = playlistData["description"];
                        //editTitle.innerHTML = "Edit " + playlistData["name"];

                        let editDescription = document.getElementById("")
                        let playlistDataHTML = document.getElementById("playlistData");
                        playlistDataHTML.insertAdjacentHTML("beforeend", '<button id="delete" style="visibility: visible" class="btn btn-danger" href="#" onclick="deletePlaylist();">Delete</button>')
                      
                        playlistDataHTML.insertAdjacentHTML("beforeend", '<button class="btn btn-primary" data-toggle="modal" data-target="#editPlaylistModal" type="button">Edit Playlist</button>')
                        for (let track of trackList) {
                            table.insertAdjacentHTML("beforeend", myTrackRow(trackNumber, track["tracks"]["id"], track["tracks"]["name"], duration(track["tracks"]["duration"])))
                            trackNumber++
                        }
                    }else{
                        for (let track of trackList) {
                            table.insertAdjacentHTML("beforeend", trackRow(trackNumber, track["tracks"]["id"], track["tracks"]["name"], duration(track["tracks"]["duration"])))
                            trackNumber++
                        }
                    }

                    console.log(isMyPlaylist)
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function putPlaylistData(data) {
    fetch('http://localhost:9092/list/update/' + listToEdit, {
        method: 'put', //post, put,delete
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
                <button class="fas fa-trash trash" data-toggle="modal" data-target="#addToPlaylist" type="button" data-button="' + id + '"></button>\
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

getPlaylist(id);

$('#addToPlaylist').on('show.bs.modal', function (e) {
    var $trigger = $(e.relatedTarget);

    let id = $trigger.data('button');
    trackToAdd = id;
    myPlaylists();
})

$(document).on("click", "#addTrack", function () {
    let playlistId = document.getElementById("listSelect").value;
    console.log("Add " + trackToAdd + " " + playlistId);

    putListData(data);
});

$('#editPlaylistModal').on('show.bs.modal', function (e) {
    document.getElementById("playlist-name").value = playlistName;
    document.getElementById("description-text").value = playlistDesc;
})

$(document).on("click", "#saveEditBtn", function () {
    // let playlistId = document.getElementById("listSelect").value;
    // console.log("Add " + trackToAdd + " " + playlistId);
    // data = {
    //     "name": name,
    //     "colour": colour
    // }
    // putListData(data);

    //putPlaylistData(data);
    console.log("SAVED")
    //location.reload();
});

function deletePlaylist(){
    fetch("http://localhost:8082/playlists/delete/"+id, {
        method: 'delete',
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
  })
window.location.replace("index.html");
}