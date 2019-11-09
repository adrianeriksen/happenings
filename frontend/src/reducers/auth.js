import { createReducer } from 'redux-starter-kit';
import {
  setPrincipal,
  authenticationFailed,
  clearPrincipal,
  authenticatingPrincipal
} from '../actions/auth';

const initialState = {
  isAuthenticated: false,
  isError: false,
  isLoading: false,
  principal: null
};

export default createReducer(initialState, {
  [authenticatingPrincipal]: state => ({
    ...state,
    isLoading: true
  }),
  [clearPrincipal]: () => ({
    ...initialState
  }),
  [setPrincipal]: (state, action) => ({
    ...state,
    isAuthenticated: true,
    isLoading: false,
    principal: action.payload
  }),
  [authenticationFailed]: state => ({
    ...state,
    isError: true,
    isLoading: false
  })
});
