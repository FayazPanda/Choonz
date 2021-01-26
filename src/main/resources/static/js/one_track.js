var trackNameInput = "";
var trackDurationInput = "";

function getAlbum(id) {
    fetch('http://localhost:8082/tracks/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function (trackData) {
                    //console.log(trackData)

                    let title = document.getElementById("trackName");

                    let trackDuration = document.getElementById("trackDuration");

                    let trackLyrics = document.getElementById("trackLyrics");

                    title.append(trackData.name);

                    trackNameInput = trackData.name;

                    trackDuration.append(duration(trackData.duration));

                    trackDurationInput = trackData.duration;

                    getArtist(trackData.album.id);

                    trackLyrics.append(trackData.lyrics);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function getArtist(id) {
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

                    let trackAlbum = document.getElementById("linkAlbumDiv");
                    let trackArtist = document.getElementById("linkArtistDiv");
                    let albumCover = document.getElementById("albumCover");
                    albumCover.src = albumData["cover"]

                    //trackAlbum.append(albumData.name + " - " +albumData.genre.name);
                    trackAlbum.insertAdjacentHTML("beforeend", '<a id="linkAlbum" href="album.html?id=' + albumData.id + '"><h3 id="trackAlbum">' + albumData.name + '</h3></a></i>');
                    trackAlbum.insertAdjacentHTML("beforeend", '<h3 id="dash">-</h3>');
                    trackAlbum.insertAdjacentHTML("beforeend", '<a id="linkGenre" href="genre.html?id=' + albumData.genre.id + '"><h3 id="trackGenre">' + albumData.genre.name + '</h3></a>');

                    trackArtist.insertAdjacentHTML("beforeend", '<a id="linkArtist" href="artist.html?id=' + albumData.artist.id + '"><p id="trackArtist">' + albumData.artist.name + '</p></a>');

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

getAlbum(id);

$('#editTrackModal').on('show.bs.modal', function (e) {
    document.getElementById("track-name").value = trackNameInput;
    document.getElementById("duration").value = trackDurationInput;
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

// Delete function
function deleteTrack(){
    fetch("http://localhost:8082/tracks/delete/"+id, {
        method: 'delete',
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
  })
window.location.replace("index.html");
}