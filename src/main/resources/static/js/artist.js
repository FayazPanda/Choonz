var artistNameInput = "";

function getArtist(id) {
    fetch('http://localhost:8082/artists/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (artistData) {
                    console.log(artistData)
                    let artistName = document.getElementById("artistName");
                    artistName.innerHTML = "Artist: " + artistData["name"];

                    let gallery = document.getElementById("allAlbums");
                    gallery.innerHTML = '';
                    artistNameInput = artistData["name"]
                    for (let album of artistData["albums"]) {
                        gallery.insertAdjacentHTML("beforeend", albumCard(album["id"], album["name"], artistData["name"], album["cover"]));
                    }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function albumCard(id, title, artist, cover) {
    return '<div class="tile">\
    <a href="/album.html?id=' + id + '">\
    <img id="albumCover" src="' + cover + '">\
    <h3>' + title + '</h3>\
    <p>By: ' + artist + '</p>\
    </a>\
</div>'
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getArtist(id);

$('#editArtistModal').on('show.bs.modal', function (e) {
    document.getElementById("arist-name").value = artistNameInput;
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
function deleteArtist(){
    fetch("http://localhost:8082/artists/delete/"+id, {
        method: 'delete',
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
  })
window.location.replace("index.html");
}