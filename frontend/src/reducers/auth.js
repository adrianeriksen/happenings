import { createReducer } from 'redux-starter-kit';
import { setPrincipal } from '../actions/auth';

const initialState = {
  isAuthenticated: false,
  principal: null
};

export default createReducer(initialState, {
  [setPrincipal]: (state, action) => ({
    ...state,
    isAuthenticated: true,
    principal: action.payload
  })
});
