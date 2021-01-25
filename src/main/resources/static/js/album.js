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

                    //artist.innerHTML = "Created by: " + albumData["artist"]["name"];
                    artist.insertAdjacentHTML("beforeend", '<a href="/artist.html?id=' +albumData["artist"]["id"] + '"><p id="artistName"> Artist: ' + albumData["artist"]["name"] + '</p></a>');
                    title.insertAdjacentHTML("beforeend", albumData["name"]);
                    genre.insertAdjacentHTML("beforeend", '<a href="/genre.html?id=' +albumData["genre"]["id"] + '"><p id="albumGenre"> Genre: ' + albumData["genre"]["name"] + '</p></a>');

                    let trackList = albumData["tracks"];
                    let table = document.getElementById("table")
                    let trackNumber = 1;
                    for (let track of trackList) {
                        table.appendChild(trackRow(trackNumber ,track["id"], track["name"], duration(track["duration"])));
                        trackNumber++;
                    }


                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function trackRow(trackNumber, id, name, duration) {
    // const a = document.createElement("a")
    // a.href = "/tracks.html?id=" + id;
    // a.className = "track"

    // const pid = document.createElement("p")
    // pid.innerText = trackNumber
    // a.appendChild(pid)

    // const pname = document.createElement("p")
    // pname.innerText = name
    // a.appendChild(pname)

    // const pduration = document.createElement("p")
    // pduration.innerText = duration
    // a.appendChild(pduration)


    return '<div class="tracks">\
                <button class="fas fa-plus" id="plus"></button>\
                <a href="/tracks.html?id='+ id +'" class="track">\
                    <p>'+ trackNumber +'</p>\
                    <p>'+name+'</p>\
                    <p>'+duration+'</p>\
                </a>\
            </div>'
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getAlbum(id);