import React from 'react';
import ReactDOM from 'react-dom';
import { createStore } from 'redux';
import './index.css';
// import App from './app/App';
import ReactTest from './react/ReactTest';
import registerServiceWorker from './registerServiceWorker';
import ReduxTest from './redux/ReduxTest';
import Provider from 'react-redux/lib/components/Provider';
import { counter } from './redux/Store';

var store = createStore(counter);
ReactDOM.render((
    <div>
        <ReactTest />
        <Provider store={store}>
            <ReduxTest />
        </Provider>
    </div>
), document.getElementById('root'));
registerServiceWorker();
