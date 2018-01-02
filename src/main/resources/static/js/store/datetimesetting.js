$('#rangestart').calendar({
	type: 'date',
	endCalendar: $('#rangeend')
});
$('#rangeend').calendar({
	type: 'date',
	startCalendar: $('#rangestart')
});

$('.ui.calendar').calendar({
	type: 'date',
	formatter: {
		date: function (date) {
			var day = ('0' + date.getDate()).slice(-2);
			var month = ('0' + (date.getMonth() + 1)).slice(-2);
			var year = date.getFullYear();
			return year + '/' + month + '/' + day;
			}
	}
})
