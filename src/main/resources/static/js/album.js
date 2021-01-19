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
                    //artist.innerHTML = "Created by: " + albumData["artist"]["name"];
                    artist.insertAdjacentHTML("beforeend", '<a href="/artist.html?id=' +albumData["artist"]["id"] + '"><p id="artistName">' + albumData["artist"]["name"] + '</p></a>');
                    title.insertAdjacentHTML("beforeend", albumData["name"]);

                    let trackList = albumData["tracks"];
                    let table = document.getElementById("table")
                    for (let track of trackList) {
                        table.appendChild(trackRow(track["id"], track["name"], duration(track["duration"])) )
                    }


                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function trackRow(id, name, duration) {
    const a = document.createElement("a")
    a.href = "/one_track.html?id=" + id;

    const pid = document.createElement("p")
    pid.innerText = id
    a.appendChild(pid)

    const pname = document.createElement("p")
    pname.innerText = name
    a.appendChild(pname)

    const pduration = document.createElement("p")
    pduration.innerText = duration
    a.appendChild(pduration)

    return a
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getAlbum(id);