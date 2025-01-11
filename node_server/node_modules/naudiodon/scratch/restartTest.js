const portAudio = require('../index.js');

let inputInstance = null;
let outputInstance = null;

/* Default configs */
const defaultAudioConfigs = {
  channelCount: 2,
  sampleFormat: portAudio.SampleFormat16Bit,
  sampleRate: 48000,
  deviceId: -1,
  closeOnError: false
  // framesPerBuffer: 1024,
};

/* To start the stream */
const startStream = () => {
  if (!inputInstance || !outputInstance) {
    throw new Error(
      'Enable "inputInstance" and "outputInstance" before starting input stream',
    );
  }
  try {
    inputInstance.pipe(outputInstance);
    inputInstance.once('data', () => {
      outputInstance.start();
      // console.log('----Output started----');
    });
    inputInstance.start();
    // console.log('----Stream started----');
  } catch (e) {
    console.error('error while starting input stream', e);
  }
};

/* To restart stream */
const restartStream = async () => {
  if (inputInstance && outputInstance) {
    return new Promise((resolve) => {
      outputInstance.once('finished', () => {
        console.log('----Stream Finished called----');
        inputInstance = null;
        outputInstance = null;
        console.log('----Stream stopped----');
        init();
        resolve();
      })
      console.log('----Stream Quit called----');
      inputInstance.quit(() => console.log('----Input stopped----'));
    });
  } else {
    init();
  }
};

const createInstance = (type) => {
if(type === 'in' || type === 'out')
  return new portAudio.AudioIO({
      [`${type}Options`]: {
        ...defaultAudioConfigs,
      },
    });
throw new Error('Invalid type');
};

const changeDevice = async () => {
  console.log('\n\n\n----Change Device called---- ');
  await restartStream();
};

/* This will run when the app is initialized */
const init = () => {
  // console.log('----Init called----');
  inputInstance = createInstance('in');
  outputInstance = createInstance('out');
  startStream();
};

init();

/* 
To replicate the device change behaviour. 
Imagine user is changing the device using dropdown menu. 
Currently I'm reinitializing with same deviceId but the error is same
*/
setInterval(async () => {
  console.log('\nrestarting...\n');
  await restartStream();
  // console.log('restarting done');
}, 1000);