import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftStatusInfo from './draft-status-info';
import DraftStatusInfoDetail from './draft-status-info-detail';
import DraftStatusInfoUpdate from './draft-status-info-update';
import DraftStatusInfoDeleteDialog from './draft-status-info-delete-dialog';

const DraftStatusInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftStatusInfo />} />
    <Route path="new" element={<DraftStatusInfoUpdate />} />
    <Route path=":id">
      <Route index element={<DraftStatusInfoDetail />} />
      <Route path="edit" element={<DraftStatusInfoUpdate />} />
      <Route path="delete" element={<DraftStatusInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftStatusInfoRoutes;
