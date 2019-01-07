var options2 = {
  shouldSort: true,
  threshold: 0.4,
  maxPatternLength: 32,
  keys: [{
    name: 'name',
    weight: 0.7
  }, {
    name: 'code',
    weight: 0.3
  }]
};

var fuse2 = new Fuse(currencies, options2)

$('.autocomplete2').each(function() {
  var ac = $(this);
  
   ac.on('click', function(e) {
    e.stopPropagation();
  })
  .on('focus keyup', search)
  .on('keydown', onKeyDown);
  
  var wrap = $('<div>')
    .addClass('autocomplete2-wrapper')
    .insertBefore(ac)
    .append(ac);
  
    var list = $('<div>')
      .addClass('autocomplete2-results')
      .on('click', '.autocomplete2-result', function(e) {
        e.preventDefault();
        e.stopPropagation();
        selectIndex2($(this).data('index'), ac);
    })
    .appendTo(wrap);
});

$(document)
  .on('mouseover', '.autocomplete2-result', function(e) {
    var index = parseInt($(this).data('index'), 10);
    if (!isNaN(index)) {
      $(this).attr('data-highlight', index);
    }
  })
  .on('click', clearResults2);

function clearResults2() {
  results = [];
  numResults = 0;
  $('.autocomplete2-results').empty();
}

function selectIndex2(index, autoinput) {
  if (results.length >= index + 1) {
    autoinput.val(results[index].code);
    clearResults2();
  }  
}

var results = [];
var numResults = 0;
var selectedIndex = -1;

function search(e) {
  if (e.which === 38 || e.which === 13 || e.which === 40) {
    return;
  }
  var ac = $(e.target);
  var list = ac.next();
  if (ac.val().length > 0) {
    results = _.take(fuse2.search(ac.val()), 7);
    numResults = results.length;
    
    var divs = results.map(function(r, i) {
        return '<div class="autocomplete2-result" data-index="'+ i +'">'
             + '<div><b>'+ r.code +'</b> - '+ r.name +'</div>'
             + '<div class="autocomplete-location">'+ r.symbol +'</div>'
             + '</div>';
     });
    
    selectedIndex = -1;
    list.html(divs.join(''))
      .attr('data-highlight', selectedIndex);

  } else {
    numResults = 0;
    list.empty();
  }
}

function onKeyDown(e) {
  var ac = $(e.currentTarget);
  var list = ac.next();
  switch(e.which) {
    case 38: // up
      selectedIndex--;
      if (selectedIndex <= -1) {
        selectedIndex = -1;
      }
      list.attr('data-highlight', selectedIndex);
      break;
    case 13: // enter
      selectIndex2(selectedIndex, ac);
      break;
    case 9: // enter
      selectIndex2(selectedIndex, ac);
      e.stopPropagation();
      return;
    case 40: // down
      selectedIndex++;
      if (selectedIndex >= numResults) {
        selectedIndex = numResults-1;
      }
      list.attr('data-highlight', selectedIndex);
      break;

    default: return; // exit this handler for other keys
  }
  e.stopPropagation();
  e.preventDefault(); // prevent the default action (scroll / move caret)
}
