function duration(durationInt) {
    let minutes = Math.floor(durationInt / 60);
    let seconds = durationInt - minutes * 60;
    let formattedNumber = ("0" + seconds).slice(-2);
    let durationString = (minutes.toString() + ":" + formattedNumber);
    return durationString;
}