module.exports = (grunt)->
  grunt.initConfig
    pkg: grunt.file.readJSON 'package.json'
    bower:
      install:
        options:
          targetDir: 'static/vendors'
          layout: 'byComponent'
          install: true
          verbose: false
          cleanTargetDir: true
          cleanBowerDir: false
          bowerOptions: {forceLatest: true}
    watch:
      coffee:
        files: ['src/main/coffee/**/*.coffee']
        tasks: ['clean:coffee', 'coffee']
      sass:
        files: ['src/main/sass/**/*.sass']
        tasks: ['clean:sass','sass']
    coffee:
      compile:
        files: [
          expand: true
          cwd: 'src/main/coffee'
          src: ['**/*.coffee']
          dest: 'static/assets/js'
          ext: '.js'
        ]
        options:
          sourceMap: true
          bare: true
    sass:
      compile:
        files: [
          expand: true
          cwd: 'src/main/sass'
          src: ['**/*.sass']
          dest: 'static/assets/css'
          ext: '.css'
        ]
    clean:
      coffee:["static/assets/js"]
      sass:["static/assets/css"]
    cssmin:
      vendors_target:
        files:
          'static/vendors/bootstrap/bootstrap.min.css':['static/vendors/bootstrap/bootstrap.css']
    uglify:
      options:
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n'
      vendors_target:
        files:
          'static/vendors/bootstrap/bootstrap.min.js':['static/vendors/bootstrap/bootstrap.js']
          'static/vendors/datatables/js/jquery.dataTables.min.js':['static/vendors/datatables/js/jquery.dataTables.js']
      my_target:
        files:
          'static/assets/js/sample.min.js':['static/assets/js/sample.js']

  grunt.loadNpmTasks 'grunt-contrib-uglify'
  grunt.loadNpmTasks 'grunt-bower-task'
  grunt.loadNpmTasks 'grunt-contrib-cssmin'
  grunt.loadNpmTasks 'grunt-contrib-coffee'
  grunt.loadNpmTasks 'grunt-contrib-watch'
  grunt.loadNpmTasks 'grunt-contrib-sass'
  grunt.loadNpmTasks 'grunt-contrib-clean'
  grunt.registerTask 'default', ['watch']
  grunt.registerTask 'init', ['bower:install', 'clean', 'coffee', 'sass', 'uglify', 'cssmin']
  return
