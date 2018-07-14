import { combineReducers } from 'redux';

import factor1 from './Factor1';
import factor2 from './Factor2';

const reducers = combineReducers({
    factor1,
    factor2
});
export default reducers;
