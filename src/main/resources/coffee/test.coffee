$ ->
  dp = $(".datepicker")
  dp.datepicker().on "changeDate", (ev) ->
    dp.val(ev.target.value).trigger "change"
    dp.datepicker "hide"

$ ->
  $("#showAll").on "click", ->
    $("#hideVacancies").removeClass("active")
    $("#hideVacancies").find("input").prop("checked", false)
    $("#hideBids").removeClass("active")
    $("#hideBids").find("input").prop("checked", false)
    $("#hideDeals").removeClass("active")
    $("#hideDeals").find("input").prop("checked", false)

$ ->
  $("#hideVacancies").on "click", ->
    $("#showAll").removeClass("active")
    $("#showAll").find("input").prop("checked", false)

$ ->
  $("#hideBids").on "click", ->
    $("#showAll").removeClass("active")
    $("#showAll").find("input").prop("checked", false)

$ ->
  $("#hideDeals").on "click", ->
    $("#showAll").removeClass("active")
    $("#showAll").find("input").prop("checked", false)

$ ->
  $("#vacanciesFilter :input").on "change", ->
    data = $("#vacanciesFilter").serialize()
    request = $.ajax(
      url: "recruiter-vacancies-filter-ajax.json"
      type: "POST"
      data: data
      dataType: "json"
    )
    request.done (data) ->
      addHtml = ""
      vacancyList = data.entry.value
      i = 0

      while i < vacancyList.length
        obj = vacancyList[i]
        addHtml += "<tr><td>" + obj.title + "</td><td>" + obj.description + "</td><td>" + obj.creationDate + "</td><td>" + obj.status + "</td><td>" + "<a href='recruiter-show-vacancy/" + obj.id + "'>link</a>" + "</td></tr>"
        i++
      $("#vacancies").find("tbody").html addHtml
      return

    request.fail ->

$ ->
  $("#prevDateLink").on "click", (event) ->
    event.preventDefault()
    $(".datepicker").val($("#prevDate").val()).trigger "change"

$ ->
  $("a#nextDateLink").on "click", (event) ->
    event.preventDefault()
    $(".datepicker").val($("#nextDate").val()).trigger "change"

