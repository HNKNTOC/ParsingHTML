

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" oncontextmenu="return false;" onselectstart="return false;"
style="-moz-user-select: none">
<head><title>
	БЕЛГОРОДСКИЙ УНИВЕРСИТЕТ КООПЕРАЦИИ, ЭКОНОМИКИ И ПРАВА - Электронное расписание занятий
</title><link rel="stylesheet" type="text/css" href="default.css" />
    <script type="text/javascript">
        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () { (i[r].q = i[r].q || []).push(arguments) }, i[r].l = 1 * new Date();
            a = s.createElement(o), m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g; 
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

        ga('create', 'UA-45282014-3', 'bukep.ru');
        ga('send', 'pageview');
    </script>
    <script type="text/javascript">
        function ScheduleClock() {
	        var _timesheet = {
		        6: {    // Суббота
			        510: "",
			        605: "1 пара", 615: "Перемена",
			        710: "2 пара", 720: "Перемена",
			        815: "3 пара", 825: "Перемена",
			        920: "4 пара", 930: "Перемена",
			        1025: "5 пара", 1035: "Перемена",
			        1130: "6 пара", 1140: "Перемена",
			        1235: "7 пара", 1236: ""
		        },
		        any: {  // Любой другой день
			        510: "",
			        605: "1 пара", 615: "Перемена",
			        710: "2 пара", 745: "Перемена",
			        840: "3 пара", 875: "Перемена",
			        970: "4 пара", 980: "Перемена",
			        1075: "5 пара", 1085: "Перемена",
			        1180: "6 пара", 1190: "Перемена",
			        1285: "7 пара", 1286: ""
		        }
	        };
	        var _months = ["Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"];
	        var _days = ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"];
	        var _delimited = true;

	        function FormatDay(date) {
		        return _days[date.getDay()];
	        }

	        function FormatDate(date) {
		        var day = date.getDate();
		        var year = date.getFullYear();
		
		        if (day < 10) day = "0" + day;
		
		        return day + "&nbsp;" + _months[date.getMonth()] + "&nbsp;" + year;
	        }

	        function FormatTime(date) {
		        var hours = date.getHours();
		        var minutes = date.getMinutes();
		        var delimiter = (_delimited) ? ":" : "&nbsp;";

		        if (hours < 10) hours = "0" + hours;
		        if (minutes < 10) minutes = "0" + minutes;
		
		        return hours + "<span style=\"font-family: courier new;\">" + delimiter + "</span>" + minutes;
	        }

	        function GetLessons(day) {
		        var result = _timesheet[day];
		        if (!result) result = _timesheet["any"];
		
		        return result; 
	        }
	
	        function GetLesson(date) {
		        var day = date.getDay();
		        var key = date.getHours() * 60 + date.getMinutes();
		        var lessons = GetLessons(day);
		
		        for (var item in lessons) {
			        if (key < item) return lessons[item];
		        }
		
		        return "&nbsp;";
	        }

	        function Update() {
		        var now = new Date();

		        document.getElementById('watch').innerHTML = FormatDate(now) + "<br />" + 
					FormatDay(now) + "<br /><span class=\"clock\">" + FormatTime(now) + "</span><br />" + 
					GetLesson(now) + "<br />" + "Знаменатель";
		        
				_delimited = !_delimited;
				setTimeout(function () { Update(); }, 500);
	        }

	        Update();
        }
        
    </script>
</head>
<body onload="ScheduleClock();">
    <form name="aspnetForm" method="post" action="Default.aspx" id="aspnetForm">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="G6oLP61G2QiZvK79C8wjRoa0+T0UguaDaPqOR47Jnh9iSPErx5/WtLOVDWq9mnTFGH0WfXE1o+6u8+tRBc/XgY2m2O8=" />

<input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="CA0B0334" />
<input type="hidden" name="__VIEWSTATEENCRYPTED" id="__VIEWSTATEENCRYPTED" value="" />
<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="7Deju6C2y++M8u4lJXMjm/CD/KbbmqRcLBW/4NwwED7fWtH8ny+foprDuhSSq3k7HAq5pdpPBEWxDwNaODRTBqiHoJtC61PCueGP2wtoZuS33nyG" />
    <table width="100%" style="border-collapse: collapse">
        <tr>
            <td style="height: 124px; background: url(images/grad.jpg) repeat-x; vertical-align: top;">
                <div class="logo">
                    Белгородский университет
                    <br />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;кооперации, экономики и права<br />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Электронное
                        расписание занятий</span>
                </div>
            </td>
            <td style="color: #666666; vertical-align: middle; text-align: center; font-family: Tahoma;
                font-size: 12px; font-weight: bold; background: url(images/grad.jpg) repeat-x;">
                <div id="watch">
                    <noscript>
                        Включите<br />
                        поддержку<br />
                        JavaScript</noscript></div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                
<table width="100%"><tr>
       <td>
           

    </td>
    <td style="text-align: right; padding-right: 5px;">
        &nbsp;</td>
    </tr>
    </table>

            </td>
        </tr>
        <tr>
            <td colspan="2">
                     

    <div id="ctl00_head_Panel11">
	

        <table id="ctl00_head_TblAll" border="0">
		<tr>
			<td></td><td align="center" valign="top"><table id="ctl00_head_Tbl_rasp1" cellspacing="0" cellpadding="0" align="Center" border="0">

			</table></td><td valign="top"></td>
		</tr>
	</table>




<table class="zv" cellspacing="20" border="0">
		<tr>
			<td rowspan="2"><table class="knock" border="0"><tr><th class="knock_1" colspan="3">Расписание звонков</th></tr><tr><td class="knock_1" colspan="2">понедельник&ndash;пятница</td><td class="knock_1">суббота</td></tr><tr><td class="n_para">1 пара</td><td class="time">08:30&ndash;09:15<br />09:20&ndash;10:05</td><td class="time">08:30&ndash;09:15<br />09:20&ndash;10:05</td></tr><tr><td colspan="3" class="dot"></td></tr><tr><td class="n_para">2 пара</td><td class="time">10:15&ndash;11:00<br />11:05&ndash;11:50</td><td class="time">10:15&ndash;11:00<br />11:05&ndash;11:50</td></tr><tr><td colspan="3" class="dot"></td></tr><tr><td colspan="2" >перерыв для питания (35 мин.)</br>11:50 - 12:25</td></tr><tr><td colspan="3" class="dot"></td></tr><tr><td class="n_para">3 пара</td><td class="time">12:25&ndash;13:10<br />13:15&ndash;14:00</td><td class="time">12:00&ndash;12:45<br />12:50&ndash;13:35</td></tr><tr><td colspan="3" class="dot"></td></tr><tr><td colspan="3" >перерыв для питания (35 мин.)</td></tr><tr><td colspan="2">14:00 - 14:35</td> <td>13:35 - 14:10 </td></tr> <tr><td colspan="3" class="dot"></td></tr><tr><td class="n_para">4 пара</td><td class="time">14:35&ndash;15:20<br />15:25&ndash;16:10</td><td class="time">14:10&ndash;14:55<br />15:00&ndash;15:45</td></tr><tr><td colspan="3" class="dot"></td></tr><tr><td class="n_para">5 пара</td><td class="time">16:20&ndash;17:05<br />17:10&ndash;17:55</td><td class="time">15:55&ndash;16:40<br />16:45&ndash;17:30</td></tr><tr><