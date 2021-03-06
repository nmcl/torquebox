require 'torquebox-messaging'

class HaJob 

  def run() 
    $stderr.puts "Job executing!"
    basedir = ENV['BASEDIR']
    basedir = basedir.gsub( %r(\\:), ':' )
    basedir.gsub!( %r(\\\\), '\\' )
    $stderr.puts "BASEDIR #{basedir}"
    touchfile = File.join( basedir,  'target', 'hajobs-touchfile.txt' )
    File.open( touchfile, 'w' ) do |f|
      f.puts( "Updated #{Time.now}" )
    end
    TorqueBox::Messaging::Queue.new('/queues/backchannel').publish('release')
  end

end

