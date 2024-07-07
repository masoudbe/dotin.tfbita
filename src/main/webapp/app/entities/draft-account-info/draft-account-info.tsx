import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-account-info.reducer';

export const DraftAccountInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftAccountInfoList = useAppSelector(state => state.draftAccountInfo.entities);
  const loading = useAppSelector(state => state.draftAccountInfo.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="draft-account-info-heading" data-cy="DraftAccountInfoHeading">
        <Translate contentKey="tfbitaApp.draftAccountInfo.home.title">Draft Account Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftAccountInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/draft-account-info/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftAccountInfo.home.createLabel">Create new Draft Account Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftAccountInfoList && draftAccountInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('documentReceiptDisciplinaryAccountId')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.documentReceiptDisciplinaryAccountId">
                    Document Receipt Disciplinary Account Id
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('documentReceiptDisciplinaryAccountId')} />
                </th>
                <th className="hand" onClick={sort('draftMainAccountId')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.draftMainAccountId">Draft Main Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('draftMainAccountId')} />
                </th>
                <th className="hand" onClick={sort('insuranceCostAccountId')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.insuranceCostAccountId">Insurance Cost Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insuranceCostAccountId')} />
                </th>
                <th className="hand" onClick={sort('justificationDisciplinaryAccountId')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.justificationDisciplinaryAccountId">
                    Justification Disciplinary Account Id
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('justificationDisciplinaryAccountId')} />
                </th>
                <th className="hand" onClick={sort('openDraftDisciplinaryAccountId')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.openDraftDisciplinaryAccountId">
                    Open Draft Disciplinary Account Id
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('openDraftDisciplinaryAccountId')} />
                </th>
                <th className="hand" onClick={sort('otherCostsAccountId')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.otherCostsAccountId">Other Costs Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('otherCostsAccountId')} />
                </th>
                <th className="hand" onClick={sort('postSwiftCostsAccountId')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.postSwiftCostsAccountId">Post Swift Costs Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('postSwiftCostsAccountId')} />
                </th>
                <th className="hand" onClick={sort('amountDeductionAccountId')}>
                  <Translate contentKey="tfbitaApp.draftAccountInfo.amountDeductionAccountId">Amount Deduction Account Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountDeductionAccountId')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftAccountInfoList.map((draftAccountInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-account-info/${draftAccountInfo.id}`} color="link" size="sm">
                      {draftAccountInfo.id}
                    </Button>
                  </td>
                  <td>{draftAccountInfo.documentReceiptDisciplinaryAccountId}</td>
                  <td>{draftAccountInfo.draftMainAccountId}</td>
                  <td>{draftAccountInfo.insuranceCostAccountId}</td>
                  <td>{draftAccountInfo.justificationDisciplinaryAccountId}</td>
                  <td>{draftAccountInfo.openDraftDisciplinaryAccountId}</td>
                  <td>{draftAccountInfo.otherCostsAccountId}</td>
                  <td>{draftAccountInfo.postSwiftCostsAccountId}</td>
                  <td>{draftAccountInfo.amountDeductionAccountId}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/draft-account-info/${draftAccountInfo.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/draft-account-info/${draftAccountInfo.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/draft-account-info/${draftAccountInfo.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.draftAccountInfo.home.notFound">No Draft Account Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftAccountInfo;
