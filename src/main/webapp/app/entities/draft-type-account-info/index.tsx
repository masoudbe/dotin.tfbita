import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftTypeAccountInfo from './draft-type-account-info';
import DraftTypeAccountInfoDetail from './draft-type-account-info-detail';
import DraftTypeAccountInfoUpdate from './draft-type-account-info-update';
import DraftTypeAccountInfoDeleteDialog from './draft-type-account-info-delete-dialog';

const DraftTypeAccountInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftTypeAccountInfo />} />
    <Route path="new" element={<DraftTypeAccountInfoUpdate />} />
    <Route path=":id">
      <Route index element={<DraftTypeAccountInfoDetail />} />
      <Route path="edit" element={<DraftTypeAccountInfoUpdate />} />
      <Route path="delete" element={<DraftTypeAccountInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftTypeAccountInfoRoutes;
