var gulp = require('gulp');

// gulp task
gulp.task('default', function(){
	console.log('Hello world');
});
// gulp task: multiple tasks
gulp.task('default2',  function(){
	console.log('Hello world2');
});

// gulp src and gulp dest
// copy file to dist
gulp.src('gulptest/*').pipe(gulp.dest('dist/'));

// gulp watch
gulp.watch('gulptest/*.js', function(event){
	console.log('Has Changes!');
	console.log(event.type);
	console.log(event.path);
});