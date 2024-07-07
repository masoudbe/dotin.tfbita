import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LicenceInfo from './licence-info';
import LicenceInfoDetail from './licence-info-detail';
import LicenceInfoUpdate from './licence-info-update';
import LicenceInfoDeleteDialog from './licence-info-delete-dialog';

const LicenceInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LicenceInfo />} />
    <Route path="new" element={<LicenceInfoUpdate />} />
    <Route path=":id">
      <Route index element={<LicenceInfoDetail />} />
      <Route path="edit" element={<LicenceInfoUpdate />} />
      <Route path="delete" element={<LicenceInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LicenceInfoRoutes;
