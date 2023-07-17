const FormatDate = (time) => {
    
    var timeString = time.toString();
    var years = timeString.slice(0, 4);
    var month = timeString.slice(4, 6);
    var days = timeString.slice(6, 8);

    var newDate = days + '/' + month + '/' + years;

    return newDate;
}

export default FormatDate;