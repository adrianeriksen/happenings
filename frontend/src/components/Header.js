import React from 'react';
import { Link } from 'react-router-dom';

import UserInformation from './UserInformation';
import ButtonLink from './shared/ButtonLink';

const Header = ({ isAuthenticated, principal }) => (
  <header>
    <h2>
      <Link to="/">Happenings</Link>
    </h2>
    {isAuthenticated ? (
      <UserInformation user={principal} />
    ) : (
      <ButtonLink to="/login">Login</ButtonLink>
    )}
  </header>
);

export default Header;
